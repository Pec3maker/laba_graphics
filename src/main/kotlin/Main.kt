import Commands.*
import java.awt.Dimension
import java.io.File
import kotlin.system.exitProcess

@OptIn(ExperimentalUnsignedTypes::class)
fun main(args: Array<String>) {
    val filename = parseArgs(args)

    initialScreen()
    val file: File = openFile(filename)
    val bitmap = Bitmap(file.readBytes().toUByteArray())
    println("Write \"help\" to see all commands.")

    while (true) {
        print(">")
        try {
            val input = readln().trim()
            processCommand(input, bitmap)
        } catch (exp: IllegalArgumentException) {
            println(exp.message)
        } catch (exp: Exception) {
            println("Unknown error. Write \"help\" to see all commands.")
            println(exp.message)
        }
    }
}

fun openFile(filename: String): File {
    try {
        println("Opened file: $filename.")
        return File(filename)
    } catch (exp: Exception) {
        println("Can't find file")
        exitProcess(1)
    }
}

fun processCommand(input: String, bitmap: Bitmap) {
    when (parseCommand(input = input)) {
        HELP -> {
            println(
                "Available commands:\n" + "help\n" + "close\n" + "save"
            )
        }

        CLOSE -> {
            exitProcess(0)
        }

        SAVE -> {
            println("Enter filename:")
            try {
                val file = File("${readln().trim()}.gns")
                file.writeBytes(bitmap.toByteArray())
                println("File saved at ${file.absolutePath}")
            } catch (exp: Exception) {
                println(exp.stackTrace)
            }
        }

        SHOW -> {
            showImage(bitmap)
        }
    }
}

private fun showImage(bitmap: Bitmap) {
    val renderer = Renderer(Dimension(bitmap.width + 3, bitmap.height + 3))
    renderer.setBitmap(bitmap)
    renderer.render()
}

fun parseCommand(input: String): Commands {
    val commandList = values()
    val commandsTitles = commandList.map { it.title }
    if (!commandsTitles.contains(input)) {
        throw IllegalArgumentException("Unknown command. Write \"help\" to see all commands.")
    }
    return commandList.first { input == it.title }
}

private fun initialScreen() {
    println(
        "╔═══╗╔═╗─╔╗╔═══╗     ╔═══╗╔═══╗╔═══╗╔═══╗╔═══╗╔═══╗\n" + "║╔═╗║║║╚╗║║║╔═╗║     ║╔═╗║║╔══╝║╔═╗║╚╗╔╗║║╔══╝║╔═╗║\n" + "║║─╚╝║╔╗╚╝║║╚══╗     ║╚═╝║║╚══╗║║─║║─║║║║║╚══╗║╚═╝║\n" + "║║╔═╗║║╚╗║║╚══╗║     ║╔╗╔╝║╔══╝║╚═╝║─║║║║║╔══╝║╔╗╔╝\n" + "║╚╩═║║║─║║║║╚═╝║     ║║║╚╗║╚══╗║╔═╗║╔╝╚╝║║╚══╗║║║╚╗\n" + "╚═══╝╚╝─╚═╝╚═══╝     ╚╝╚═╝╚═══╝╚╝─╚╝╚═══╝╚═══╝╚╝╚═╝"
    )
    println("---------------------------------------------------")
    println("For computer graphic subject")
}

fun parseArgs(args: Array<String>): String {
    when {
        args.isEmpty() -> {
            throw IllegalArgumentException("There must be at least one argument")
        }

        !args.first().endsWith(".gns") -> {
            throw IllegalArgumentException("Argument must be a *.gns file")
        }

        else -> {
            return args.first()
        }
    }
}
