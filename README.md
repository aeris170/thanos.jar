# thanos.jar
**Inspired by [thanos.js](https://github.com/anandundavia/thanos-js)**

Thanos has now arrived on your PC and he will bring havoc to your files.
He will wipe out exactly half of your files when he snaps fingers wearing the infinity gauntlet.

You are lucky though, he can only carry out destruction in the directory he is in.

## Install

Download the [thanos.jar](https://github.com/aeris170/thanos.jar/raw/master/thanos.jar) file.

## Usage

- Read the [DISCLAIMER](https://github.com/aeris170/thanos.jar/blob/master/DISCLAIMER) first!
- Put the file to the directory you want to snap fingers in, run the file, press snap fingers.

## Technical Details

- Yes, It deletes the files. [ for those who are confused about what this application does ]

- It traverses the whole directory structure down from where the command is executed. So files inside child directories might be deleted.

- Exactly half of the files are deleted. Each file is given a `chance` at random and either the top 50% of the files or bottom 50% of files are chosen to be deleted.

## Dependencies
  * [JLine 2.11](https://github.com/jline/jline2)

## Screenshots
![thanos_1](https://user-images.githubusercontent.com/25724155/60385503-8d6acc00-9a92-11e9-82d5-c8207d7f34e0.png)