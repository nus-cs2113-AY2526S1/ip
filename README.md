# NUS CS2113 individual project (iP)

> by Yik Jin ([@yikjin](https://github.com/yikjin))

This repository hosts my own [individual project (iP)](
https://nus-cs2113-ay2526s1.github.io/website/admin/ip-overview.html) code for
[NUS' "CS2113: Software Engineering & Object-Oriented Programming" course](
https://nusmods.com/courses/CS2113/software-engineering-object-oriented-programming).

## License

This is NOT an open source project. See [LICENSE.md](./LICENSE.md) for more details.

## Security policy

The security policy for this project can be found in [SECURITY.md](./SECURITY.md).

## Code of conduct

The code of conduct for this project can be found in [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md).

---

<details>
<summary>View README from upstream template iP</summary>

# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are
instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project
   first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained
   in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code
   editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the
   below as the output:
   ```
   Hello from
    ____        _
   |  _ \ _   _| | _____
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move
Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle)
expect to find Java files.

</details>
