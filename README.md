# Java IO

This project is used to practice Java IO concepts.
 
## I/O Streams

- A program uses an input stream to read data from a source.
- A program uses an output stream to write data to a destination.

## Byte Streams

Programs use byte streams to perform input and output of 8-bit bytes. All byte stream classes 
are descended from InputStream and OutputStream (abstract classes).

File I/O byte streams classes FileInputStream and FileOutputStream (extends InputStream and OutputStream).
- Read int variable holds a byte value in its last 8 bits

## Character Streams

The Java platform stores character values using Unicode conventions. Character stream I/O 
automatically translates this internal format to and from the local character set. In 
Western locales, the local character set is usually an 8-bit superset of ASCII.

- All character stream classes are descended from Reader and Writer. 
- As with byte streams, there are character stream classes that specialize in file I/O: FileReader and FileWriter.
- Read int variable holds a character value in its last 16 bits
- Character streams are often "wrappers" for byte streams. The character stream uses the byte stream 
to perform the physical I/O, while the character stream handles translation between characters
 and bytes. FileReader, for example, uses FileInputStream, while FileWriter uses FileOutputStream.

## Line-Oriented I/O

Character I/O usually occurs in bigger units than single characters. One common unit is the line: a string of 
characters with a line terminator at the end. A line terminator can be a carriage-return/line-feed sequence ("\r\n"), 
a single carriage-return ("\r"), or a single line-feed ("\n"). Supporting all possible line terminators allows programs 
to read text files created on any of the widely used operating systems.

Let's modify the CopyCharacters example to use line-oriented I/O. To do this, we have to use two classes we haven't 
seen before, BufferedReader and PrintWriter.

## Buffered Streams

Most of the examples we've seen so far use unbuffered I/O. This means each read or write request is handled directly by 
the underlying OS. This can make a program much less efficient, since each such request often triggers disk access, 
network activity, or some other operation that is relatively expensive.

To reduce this kind of overhead, the Java platform implements buffered I/O streams. Buffered input streams read data 
from a memory area known as a buffer; the native input API is called only when the buffer is empty. Similarly, 
buffered output streams write data to a buffer, and the native output API is called only when the buffer is full.
 
A program can convert an unbuffered stream into a buffered stream using the wrapping idiom we've used several times now,
where the unbuffered stream object is passed to the constructor for a buffered stream class. Here's how you might
modify the constructor invocations in the CopyCharacters example to use buffered I/O:

inputStream = new BufferedReader(new FileReader("xanadu.txt"));
outputStream = new BufferedWriter(new FileWriter("characteroutput.txt"));
There are four buffered stream classes used to wrap unbuffered streams: BufferedInputStream and BufferedOutputStream 
create buffered byte streams, while BufferedReader and BufferedWriter create buffered character streams. 

## Flushing Buffered Streams

It often makes sense to write out a buffer at critical points, without waiting for it to fill. This is known as 
flushing the buffer.

Some buffered output classes support autoflush, specified by an optional constructor argument. When autoflush is enabled,
 certain key events cause the buffer to be flushed. For example, an autoflush PrintWriter object flushes the buffer 
 on every invocation of println or format. See Formatting for more on these methods.

To flush a stream manually, invoke its flush method. The flush method is valid on any output stream, but has no effect 
unless the stream is buffered.

## Scanning and Formatting

Programming I/O often involves translating to and from the neatly formatted data humans like to work with. 
To assist you with these chores, the Java platform provides two APIs. 
- The scanner API breaks input into individual tokens associated with bits of data. 
- The formatting API assembles data into nicely formatted, human-readable form.

# Breaking Input into Tokens

By default, a scanner uses white space to separate tokens.

## Translating Individual Tokens

Scanner also supports tokens for all of the Java language's primitive types (except for char), as well as BigInteger and BigDecimal.

## Formatting

Stream objects that implement formatting are instances of either PrintWriter, a character stream class, or PrintStream, 
a byte stream class.

Note: The only PrintStream objects you are likely to need are System.out and System.err. (See I/O from the Command 
Line for more on these objects.) When you need to create a formatted output stream, instantiate PrintWriter, 
not PrintStream.

Like all byte and character stream objects, instances of PrintStream and PrintWriter implement a standard set of
 write methods for simple byte and character output. In addition, both PrintStream and PrintWriter implement the 
 same set of methods for converting internal data into formatted output. Two levels of formatting are provided:

- print and println format individual values in a standard way.
- format formats almost any number of values based on a format string, with many options for precise formatting.

https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax

For example all format specifiers begin with a % and end with a 1- or 2-character conversion that specifies the 
kind of formatted output being generated. The three conversions used here are:

- %d formats an integer value as a decimal value.
- %f formats a floating point value as a decimal value.
- %n outputs a platform-specific line terminator.

Here are some other conversions:

- %x formats an integer as a hexadecimal value.
- %s formats any value as a string.
- %tB formats an integer as a locale-specific month name.

## I/O from the Command Line

A program is often run from the command line and interacts with the user in the command line environment. The Java platform supports this kind of interaction in two ways: 
- Standard Streams 
- Console.

## Standard Streams

Standard Streams are a feature of many operating systems. By default, they read input from the keyboard and write output
 to the display. They also support I/O on files and between programs, but that feature is controlled by the command 
 line interpreter, not the program. 
 
## Console

A more advanced alternative to the Standard Streams is the Console. This is a single, predefined object of type Console that 
has most of the features provided by the Standard Streams, and others besides. The Console is particularly useful for secure 
password entry. The Console object also provides input and output streams that are true character streams, through its reader 
and writer methods.

Before a program can use the Console, it must attempt to retrieve the Console object by invoking System.console(). 
If the Console object is available, this method returns it. If System.console returns NULL, then Console operations are 
not permitted, either because the OS doesn't support them or because the program was launched in a noninteractive 
environment.

If the virtual machine is started from an interactive command line without redirecting the standard input and output 
streams then its console will exist and will typically be connected to the keyboard and display from which the virtual
machine was launched. If the virtual machine is started automatically, for example by a background job scheduler,
then it will typically not have a console.
 
## Data Streams

The Java DataOutputStream class enables you to write Java primitives to OutputStream's instead of only bytes. 
You wrap an OutputStream in a DataOutputStream and then you can write primitives to it. 
That is why it is called a DataOutputStream - because you can write int, long, float and double values to the 
OutputStream, and not just raw bytes.
 
## Object Streams

The Java ObjectInputStream class (java.io.ObjectInputStream) enables you to read Java objects from an InputStream 
instead of just raw bytes. You wrap an InputStream in a ObjectInputStream and then you can read objects from it.
 Of course the bytes read must represent a valid, serialized Java object. Otherwise reading objects will fail.

## Java NIO Files

The Java NIO Files class (java.nio.file.Files) provides several methods for manipulating files in the file system. 
This Java NIO Files tutorial will cover the most commonly used of these methods. The Files class contains many methods,
so check the JavaDoc too, if you need a method that is not described here. The Files class just might have a method 
for it still.

The java.nio.file.Files class works with java.nio.file.Path instances.

#### Files.exists()

The Files.exists() method checks if a given Path exists in the file system.

#### Files.createDirectory()

The Files.createDirectory() method creates a new directory from a Path instance.

#### Files.copy()

The Files.copy() method copies a file from one path to another.

Overwriting Existing Files: Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING)

#### Files.move()

The Java NIO Files class also contains a function for moving files from one path to another.

#### Files.delete()

The Files.delete() method can delete a file or directory.

#### Files.walkFileTree()

The Files.walkFileTree() method contains functionality for traversing a directory tree recursively.

#### Deleting Directories Recursively

The Files.walkFileTree() can also be used to delete a directory with all files and subdirectories inside it.  

### Reference 
[Java IO](https://docs.oracle.com/javase/tutorial/essential/io/)
[Jenkov Java Files](http://tutorials.jenkov.com/java-nio/files.html)