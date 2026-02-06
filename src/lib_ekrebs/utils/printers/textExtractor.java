package lib_ekrebs.utils.printers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class textExtractor
{
	private String dir_path;
	private File directory;
	private Random random;

	public textExtractor(String texts_directory_path) throws IllegalArgumentException
	{
		dir_path = texts_directory_path;
		directory = new File(texts_directory_path);
        random = new Random();
		if (!directory.exists() || ! directory.isDirectory())
			throw new IllegalArgumentException("Bad directory path: \'"+texts_directory_path+"\'\nthe directory doesn't exist.");
	}

	public List<String> getText(String path) throws IOException
	{
		File text;
		Path text_path;

        if (path == null)
            throw new IllegalStateException("no .txt File found in\'"+path+"\'");
		text = new File(path);
		if (!text.exists() || !text.isFile())
			throw new IllegalStateException("no .txt File found in\'"+path+"\'");
		text_path = text.toPath();
        return (Files.readAllLines(text_path));
	}

	public String GetTextToOneString(String path) throws IOException
	{
		List<String> Lines;
		StringBuilder result;

		Lines = getText(path);
		result = new StringBuilder();
		for(String line : Lines)
		{
			result.append(line).append("\n");
		}
		return (result.toString());
	}

	public List<String> getRandomText() throws IOException
	{
        File[] texts;
		File selectedText;
		Path text_path;
		
		texts = directory.listFiles((dir, name) -> name.endsWith(".txt")); 				//  - lamda expression !
        if (texts == null || texts.length == 0)
            throw new IllegalStateException("no .txt File found in\'"+dir_path+"\'");
        selectedText = texts[random.nextInt(texts.length)];
		text_path = selectedText.toPath();
        return (Files.readAllLines(text_path));
    }

	public String getRandomTextToOneString() throws IOException    						// - must trow because uses a function that throws !
	{
		List<String> Lines;
		StringBuilder result;

		Lines = getRandomText();
		result = new StringBuilder();
		for(String line : Lines)
		{
			result.append(line).append("\n");
		}
		return (result.toString());
	}
}
