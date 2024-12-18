package com.HappyCow.ShellUtilities;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.HappyCow.MiniNanoShell.MiniNanoShell;

/**
* FolderManagement class, for creating or removing files or folders.
*/
public class ShellUtilities
{

	/**
	* Function to delete a file.
	*
	* @param filename The file name to delete.
	*/
	public static void rm(String filename)
	{
		File file = new File(MiniNanoShell.getCurrentDir(), filename);
		if (file.exists() && file.isFile())
		{
			if (!file.delete())
			{
				System.out.println("Failed to delete file.");
			}
		}
		else
		{
			System.out.println("File not found: "+filename);
		}
	}

	/**
	* Function to create a empty folder
	*
	* @param dirname The folder name to create.
	*/
	public static void mkdir(String dirname)
	{
		File dir = new File(MiniNanoShell.getCurrentDir(), dirname);
		if (!dir.mkdir())
		{
			System.out.println("Failed to create directory or it already exists.");
		}
	}

	/**
	* Function to remove a folder.
	*
	* @param dirname The folder name to delete.
	*/
	public static void rmdir(String dirname)
	{
		File dir = new File(MiniNanoShell.getCurrentDir(), dirname);
		if (dir.exists() && dir.isDirectory())
		{
			if (!dir.delete())
			{
				System.out.println("Failed to delete directory. Make sure it is empty.");
			}
		}
		else
		{
			System.out.println("Directory not found:"+dirname);
		}
	}

	/**
	* Function to view a file.
	*
	* @param filename The file name to view.
	*/
	public static void cat(String filename)
	{
		File file = new File(MiniNanoShell.getCurrentDir(), filename);
		if (file.exists() && file.isFile())
		{
			try (Scanner fileScanner = new Scanner(file))
			{
				while (fileScanner.hasNextLine())
				{
					System.out.println(fileScanner.nextLine());
				}
			}
			catch (IOException e)
			{
				System.out.println("Error reading file: "+e.getMessage());
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("File not found: "+filename);
		}
	}

	/**
	* Function to list files and directories. If a path is provided, it lists the contents
	* of the specified directory. If no path is provided it lists the contents from the current directory.
	*
	* @param path Directory path to list contents from, use just "" for the current directory;
	*/
	public static void list(String path)
	{
		File directory = path.isEmpty() ? MiniNanoShell.getCurrentDir() : new File(MiniNanoShell.getCurrentDir(), path);

		if (!directory.exists() || !directory.isDirectory())
		{
			System.out.println("Directory not found: "+path);
			return;
		}

		File[] files = directory.listFiles();
		if (files != null && files.length > 0)
		{
			for (File file : files)
			{
				System.out.println((file.isDirectory() ? "[dir ]: " : "[file]: ") + file.getName());
			}
		}
		else
		{
			System.out.println("No files or directories found.");
		}
	}

	/**
	* Displays the time and date.
	*/
	public static void clock()
	{
		LocalDateTime localTime = LocalDateTime.now();
		final DateTimeFormatter clockFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		String localClock = localTime.format(clockFormat);

		System.out.println(localClock);
	}
}
