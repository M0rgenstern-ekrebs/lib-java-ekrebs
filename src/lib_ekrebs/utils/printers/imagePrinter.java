package lib_ekrebs.utils.printers;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static lib_ekrebs.defines.ECMA_48.ECMA_SGR_codes.*;

public class imagePrinter 
{
	public static void print_image(String Path)
	{
		File imageFile;
		int targetWidth;
		BufferedImage image;
		try 
		{
			if(Path == null)
				return ;
			if (Path.startsWith("~"))
				Path = Path.replace("~", System.getProperty("user.home"));
			imageFile = new File(Path);
			if (!imageFile.exists())
				throw new IOException("Failed to load image. Path: "+Path);
			targetWidth = 80;
			image = image_rescale(imageFile, targetWidth);
			if (image == null)
			throw new IOException("Failed to load image. Path: " + imageFile.getAbsolutePath());
			print_buffered_image(image);
		}
		catch (IOException e) 
		{
			System.out.println("Error: " + e.getMessage());
		}
	}

	private static BufferedImage image_rescale(File imageFile, int targetWidth) throws IOException
	{
		try
		{
			BufferedImage image = ImageIO.read(imageFile);
			int targetHeight = (int) ((double) image.getHeight() * targetWidth / image.getWidth());
			Image scaled = image.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);

			BufferedImage resized = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
				
			// **FIX 1**: Proper Graphics2D + correct drawImage overload
			Graphics2D g2d = resized.createGraphics();
			g2d.drawImage(scaled, 0, 0, targetWidth, targetHeight, null);
			g2d.dispose();
			return (resized);
		}
		catch (IOException e)
		{
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			throw new IOException("image_rescale ImageIO.read() failed\n");
		}
	}

	private static void print_buffered_image(BufferedImage buffered_image)
	{
		String msg;
		int rgb0, rgb1;
		int r0, r1;
		int g0, g1;
		int b0, b1;

		msg = "";
		r0 = g0 = b0 = r1 = g1 = b1 = 0;
		for (int y = 0; y < buffered_image.getHeight(); y+=2)
		{

			for (int x = 0; x < buffered_image.getWidth(); x++)
			{
				rgb0 = buffered_image.getRGB(x, y);
				rgb1 = buffered_image.getRGB(x, y+1);
					r1 = (rgb1 >> 16) & 0xFF;
					g1 = (rgb1 >> 8) & 0xFF;
					b1 = rgb1 & 0xFF;

					r0 = (rgb0 >> 16) & 0xFF;
					g0 = (rgb0 >> 8) & 0xFF;
					b0 = rgb0 & 0xFF;
					msg += SGR_COMBINE(RGB(r0, g0, b0), BG_RGB(r1, g1, b1))+"▀"+ RESET;
			}
			msg += "\n";
		}
		System.out.print(msg);
	}
}

