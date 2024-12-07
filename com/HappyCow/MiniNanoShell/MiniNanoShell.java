/**
*	NanoShell, Simple UNIX shell made in Java.
*	Copyright (C) 2024  Dv5N
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package com.HappyCow.MiniNanoShell;

import java.util.Scanner; // Import for running commands.
import java.io.File; // Import for files and directories management.

// Local imports.
import com.HappyCow.ShellUtilities.ShellUtilities; // For shell utilities.

/**
* Main class.
*/
public class MiniNanoShell
{
	private static File currentDirectory = new File(System.getProperty("user.dir"));

	public final String version = "0.1.1";
	public final String promptColor = "\033[32m"; // Green.
	public static File getCurrentDir() {return currentDirectory;}

	public static void main(String[] args)
	{
		new MiniNanoShell().runShell("HappyCow :-) "); // Run the shell with the specified prompt.
	}

	/**
	* Main shell function.
	*/
	public void runShell(String prompt)
	{
		final Scanner cmdScanner = new Scanner(System.in);
		while (true)
		{
			System.out.print(promptColor+prompt+"\033[0m");
			final String cmd = cmdScanner.nextLine().trim();

			if (cmd.isEmpty())
			{
				continue;
			}

			else if (cmd.equals("exit"))
			{
				System.out.println("Exiting now... Be happy :-)");
				break;
			}
			executeCommand(cmd);
		}
		cmdScanner.close();
	}

	/**
	* Function to handle commands.
	*/
	private void executeCommand(String cmd)
	{
		if (cmd.contains("&&"))
		{
			String[] commands = cmd.split("&&");
			for (String singleCommand : commands)
			{
				singleCommand = singleCommand.trim();
				if (!singleCommand.isEmpty())
				{
					executeCommand(singleCommand);
				}
			}
			return;
		}

		if (cmd.contains(" "))
		{
			String[] cmdParts = cmd.split(" ", 2);
			String command = cmdParts[0].trim();
			String argument = cmdParts[1].trim();

			switch (command)
			{
				case "cd":
					changeDir(argument); // Change directory.
					break;
				case "ls":
					ShellUtilities.list(argument); // Lists contents from the specified path.
					break;
				case "mkdir":
					ShellUtilities.mkdir(argument); // Create empty folder.
					break;
				case "rmdir":
					ShellUtilities.rmdir(argument); // Remove empty folder.
					break;
				case "rm":
					ShellUtilities.rm(argument); // Remove a specified file.
					break;
				case "cat":
					ShellUtilities.cat(argument); // Displays a specified file's text.
					break;
				default:
					System.out.println("Unknown command: "+command);
					System.out.println("Type \"help\" for a list of commands.");
			}
		}
		else
		{
			handleMoreCommands(cmd);
		}
	}

	private void handleMoreCommands(String cmd)
	{
		switch (cmd)
		{
			case "help":
				// Using just one call to "System.out.println(...);" for efficiency.
				System.out.println("MiniNanoShell, version: "+version+
					"\n(Note: (o) = Optional argument)\n"+
					"Commands:\n"+
					"1. exit - Exits the shell.\n"+
					"2. help - Shows a list of commands.\n"+
					"3. clear - Clear the display.\n"+
					"4. date - Displays the current date and time.\n"+
					"5. ls (o)<path> - Lists contents in the specified path.\n"+
					"6. cd <path> - Change current directory to <path>.\n"+
					"7. mkdir <name> - Create a folder.\n"+
					"8. rmdir <name> - Delete a empty folder\n"+
					"9. rm <filename> - Delete a file\n"+
					"10. cat <filename> - Display a text file.");
				break;
			case "cd":
				System.out.println("Use: cd <path>"); // Default if no path is provided.
				break;
			case "clear":
				System.out.print("\033[H\033[2J\033[3J"); System.out.flush(); // Clear screen.
				break;
			case "date":
				ShellUtilities.clock();
				break;
			case "mkdir":
				System.out.println("Use: mkdir <folder name>"); // Default if no folder is provided.
				break;
			case "rmdir":
				System.out.println("Use: rmdir <folder name>"); // Default if no folder is provided.
				break;
			case "rm":
				System.out.println("Use: rm <filename>"); // Default if no filename is provided.
				break;
			case "cat":
				System.out.println("Use: cat <filename>"); // Default if no filename is provided.
				break;
			case "ls":
				ShellUtilities.list(""); // List contents from the current directory.
				break;
			default:
				System.out.println("Unknown command: "+cmd);
				System.out.println("Type \"help\" for a list of commands.");
			}
		}

	/**
	* Function to change directory to specified path.
	*
	* @param path The target directory path to change to.
	*/
	private void changeDir(String path)
	{
		File newDirectory = new File(currentDirectory, path);
		if (newDirectory.isDirectory() && newDirectory.exists())
		{
			currentDirectory = newDirectory;
		}
		else
		{
			System.out.println("Directory not found: "+path+"\nPlease verify the path and try again.");
		}
	}
}
