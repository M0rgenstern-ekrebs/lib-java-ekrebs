package lib_ekrebs.utils.printers;

import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.awt.*;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import static lib_ekrebs.defines.ECMA_48.ECMA_SGR_codes.*;

public class gifPrinter
{
	public static void print_gif(String Path)
	{
		try
		{
			if(Path == null || Path.isEmpty())
				return;
			
			if (Path.startsWith("~"))
				Path = Path.replace("~", System.getProperty("user.home"));
				
			File imageFile = new File(Path);
			if (!imageFile.exists())
				throw new IOException("GIF not found: " + Path);
				
			// Read GIF frames
			BufferedImage[] frames = gif_rescale(imageFile, 80);
			if (frames == null || frames.length == 0)
				throw new IOException("Failed to load GIF frames");
				
			print_gif_animation(frames);
			
		}
		catch (IOException e)
		{
			System.err.println("GIF Error: " + e.getMessage());
		}
	}

	private static BufferedImage[] gif_rescale(File gifFile, int targetWidth) throws IOException
	{
		try
		{
			// Use ImageReader for GIF frames (handles animation)
			ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
			ImageInputStream iis = ImageIO.createImageInputStream(gifFile);
			reader.setInput(iis);
			
			int frameCount = reader.getNumImages(true);
			BufferedImage[] frames = new BufferedImage[frameCount];
			
			for (int i = 0; i < frameCount; i++)
			{
				BufferedImage frame = reader.read(i);
				int targetHeight = (int) ((double) frame.getHeight() * targetWidth / frame.getWidth());
				
				// Scale this frame
				java.awt.Image scaled = frame.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_DEFAULT);
				BufferedImage resized = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2d = resized.createGraphics();
				g2d.drawImage(scaled, 0, 0, targetWidth, targetHeight, null);
				g2d.dispose();
				
				frames[i] = resized;
			}
			
			iis.close();
			reader.dispose();
			return frames;
			
		} catch (IOException e)
		{
			throw new IOException("GIF scaling failed: " + e.getMessage());
		}
	}

	private static void print_gif_animation(BufferedImage[] frames)
	{
		StringBuilder[] frameBuffers = new StringBuilder[frames.length];
		
		// Pre-render all frames to strings
		for (int f = 0; f < frames.length; f++)
		{
			frameBuffers[f] = new StringBuilder();
			BufferedImage frame = frames[f];
			
			for (int y = 0; y < frame.getHeight() - 1; y += 2)
			{
				for (int x = 0; x < frame.getWidth(); x++)
				{
					int rgb0 = frame.getRGB(x, y);
					int rgb1 = frame.getRGB(x, y + 1);
					
					int r0 = (rgb0 >> 16) & 0xFF, g0 = (rgb0 >> 8) & 0xFF, b0 = rgb0 & 0xFF;
					int r1 = (rgb1 >> 16) & 0xFF, g1 = (rgb1 >> 8) & 0xFF, b1 = rgb1 & 0xFF;
					
					frameBuffers[f].append(SGR_COMBINE(RGB(r0, g0, b0), BG_RGB(r1, g1, b1))).append("▀").append(RESET);
				}
				frameBuffers[f].append("\n");
			}
		}
		
		// Animation loop with cursor reset
		Scanner scanner = new Scanner(System.in);
		System.out.print("\033[?25l");  // Hide cursor
		
		while (true)
		{
			for (int f = 0; f < frames.length; f++)
			{
				// **CURSOR RESET**: Move to start position
				System.out.print("\033[" + (frames[0].getHeight() + 1) + "A\033[1G");
				System.out.print(frameBuffers[f]);
				System.out.flush();
				
				// Delay between frames (adjust for speed)
				try
				{
					Thread.sleep(100);
				} 
				catch (InterruptedException e)
				{

				}
				finally
				{
					scanner.close();
				}
			}
		}
	}
}
