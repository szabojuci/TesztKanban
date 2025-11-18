// Represents available game engines for selection.
enum GameEngine {
    UNITY,
    UNREAL_ENGINE,
    GODOT,
    CUSTOM_ENGINE
}

/**
 * Handles the selection of a game engine and the initialization of a new project.
 * This class simulates the process by generating a descriptive log of the steps.
 */
public class GameProjectInitializer {

    /**
     * Initializes a new game project with the specified engine and project name.
     *
     * @param engine The chosen game engine.
     * @param projectName The name of the new project.
     * @return A string detailing the initialization process and outcome.
     */
    public static String initializeProject(GameEngine engine, String projectName) {
        if (projectName == null || projectName.trim().isEmpty()) {
            return "Error: Project name cannot be empty. Please provide a valid name.";
        }

        StringBuilder log = new StringBuilder();
        log.append("--- Game Project Initialization ---\n");
        log.append("Project Name: '").append(projectName).append("'\n");
        log.append("Selected Engine: ").append(engine.name()).append("\n\n");

        log.append("Initialization Steps:\n");
        switch (engine) {
            case UNITY:
                log.append("  - Creating Unity project directory structure.\n");
                log.append("  - Importing default Unity packages (e.g., UI, TextMeshPro).\n");
                log.append("  - Setting up initial scene and project settings.\n");
                log.append("  - Generating C# solution files.\n");
                break;
            case UNREAL_ENGINE:
                log.append("  - Creating Unreal Engine project files (.uproject).\n");
                log.append("  - Compiling engine modules and shaders for the project.\n");
                log.append("  - Setting up default maps, content, and C++ classes.\n");
                log.append("  - Configuring build system (e.g., Visual Studio/Xcode project).\n");
                break;
            case GODOT:
                log.append("  - Creating Godot project folder and 'project.godot' file.\n");
                log.append("  - Setting up default scenes and resources.\n");
                log.append("  - Initializing GDScript environment and editor settings.\n");
                log.append("  - Preparing export templates.\n");
                break;
            case CUSTOM_ENGINE:
                log.append("  - Setting up custom engine's core project directory.\n");
                log.append("  - Linking engine libraries and build configuration.\n");
                log.append("  - Creating initial game modules and asset pipelines.\n");
                log.append("  - Defining project-specific configuration files.\n");
                break;
        }
        log.append("\nProject '").append(projectName).append("' has been successfully initialized using ").append(engine.name()).append(".\n");
        log.append("You can now open the project in the respective engine editor and start development!");

        return log.toString();
    }
}
