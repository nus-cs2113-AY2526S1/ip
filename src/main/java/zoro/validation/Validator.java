package zoro.validation;

import java.util.Arrays;
import java.util.List;
import zoro.model.Task;


public class Validator {


    //TTODO
    public static ValidationResult validateTodoCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");

        if (userInputSplit.length < 2) {
            return ValidationResult.fail("You can't train without knowing what you're training for. " +
                    "Tell me what task you need to do.");
        }

        String description = userInputSplit[1];
        if (description.isEmpty()) {
            return ValidationResult.fail("That's as empty as my sense of direction. " +
                    "Give me a real task description");
        }

        return ValidationResult.success();
    }


    //MARK
    public static ValidationResult validateMarkCommand(String userInput, List<Task> tasks) {
        String[] userInputSplit = userInput.split(" ");

        if (userInputSplit.length < 2) {
            return ValidationResult.fail("Which task are you marking? I may get lost easily, " +
                    "but at least I know what I'm looking for.");
        }

        try {
            int markIndex = Integer.parseInt(userInputSplit[1]);
            if (markIndex > tasks.size() || markIndex <= 0) {
                return ValidationResult.fail("That index doesn't exist. Even I wouldn't get that lost.");
            }
            return ValidationResult.success();
        } catch (NumberFormatException e) {
            return  ValidationResult.fail("Use numbers, not letters. I may use swords," +
                    " but I still know how to count.");
        }

    }


    //DEADLINE
    public static ValidationResult validateDeadlineCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        List<String> argsList = Arrays.asList(userInputSplit);
        if (argsList.size() < 4) {
            return ValidationResult.fail("A swordsman needs proper form. " +
                    "Your deadline command is missing parts - fix it!");
        }

        int byIndex = -10;
        for (int i=1; i<argsList.size(); i++) {
            if (argsList.get(i).equals("/by")) {
                byIndex = i;
                break;
            }
        }
        if (byIndex < 0) {
            return ValidationResult.fail("Where's the '/by' keyword? Even Sanji could follow instructions" +
                    " better than this.");
        }
        if (byIndex == 1) {
            return ValidationResult.fail("Description first, then '/by'." +
                    " It's like sword stance - order matters.");
        }
        if (byIndex == argsList.size()-1) {
            return ValidationResult.fail("You put '/by' but forgot the actual deadline." +
                    "That's like drawing your sword and not swinging it.");
        }

        String description = String.join(" ", argsList.subList(1, byIndex)).trim();
        if (description.isEmpty()) {
            return ValidationResult.fail("Empty description? I don't train for nothing," +
                    " and you shouldn't schedule nothing either.");
        }

        String deadline = String.join(" ", argsList.subList(byIndex+1, argsList.size())).trim();
        if (deadline.isEmpty()) {
            return ValidationResult.fail("No deadline given? How are you supposed to" +
                    " push yourself without a target?");
        }

        return ValidationResult.success();
    }


    //EVENT
    public static ValidationResult validateEventCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit.length < 6) {
            return ValidationResult.fail("This command is weaker than my sense of direction." +
                    " Add more details!");
        }

        String[] args = userInput.split(" ");
        List<String> argsList = Arrays.asList(args);

        int fromIndex = -10;
        int toIndex = -10;

        for (int i=1; i<argsList.size(); i++) {
            if (argsList.get(i).equals("/from")) {
                fromIndex = i;
            } else if (argsList.get(i).equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex < 0) {
            return ValidationResult.fail("Missing '/from' keyword. Every battle has a start -" +
                    " so should your event.");
        }
        if (toIndex < 0) {
            return ValidationResult.fail("Missing '/to' keyword. Every training session has an end" +
                    " - so should your event.");
        }
        if (fromIndex == 1 || toIndex == 1) {
            return ValidationResult.fail("Event name goes first!" +
                    " It's like announcing your attack before you strike.");
        }
        if (fromIndex == argsList.size()-1 ||  toIndex == argsList.size()-1) {
            return ValidationResult.fail("Don't end with keywords. That's like stopping mid-sword swing.");
        }
        if (fromIndex >= toIndex) {
            return ValidationResult.fail("'from' should come before '/to'. Even I know that much about directions.");
        }

        String description = String.join(" ", argsList.subList(1, fromIndex));
        String startTime = String.join(" ", argsList.subList(fromIndex+1, toIndex));
        String endTime = String.join(" ", argsList.subList(toIndex+1, argsList.size()));

        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            return ValidationResult.fail("Empty fields are like dull blades - useless. Fill them all in properly");
        }

        return ValidationResult.success();

    }

    public static ValidationResult validateDeleteCommand(String userInput, List<Task> tasks) {
        String[] userInputSplit = userInput.split(" ");

        if (userInputSplit.length < 2) {
            return ValidationResult.fail("You can't defeat your enemy if you don't have any" +
                    "Tell me what you need me to delete.");
        }

        String description = userInputSplit[1];
        if (description.isEmpty()) {
            return ValidationResult.fail("That's as empty as my sense of direction. " +
                    "Give me the name of your opponent, I'm a bounty hunter after all.");
        }

        try {
            int deleteIndex = Integer.parseInt(userInputSplit[1]);
            //System.out.println("DELETE INDEX: " + deleteIndex);

            if (deleteIndex > tasks.size() || deleteIndex <= 0) {
                return ValidationResult.fail("That index doesn't exist. Even I wouldn't get that lost.");
            }
            return ValidationResult.success();
        } catch (NumberFormatException e) {
            return ValidationResult.fail("Use numbers, not letters. I may use swords," +
                    " but I still know how to count.");
        }
    }

    public static ValidationResult validateFindCommand(String userInput, List<Task> tasks) {
        String[] userInputSplit = userInput.split(" ");

        if (userInputSplit.length < 2) {
            return ValidationResult.fail("I can't find what you're looking for if you don't give me any directions!" +
                    "Enter a keyword next time.");
        }

        String keyword = userInputSplit[1];
        if (keyword.isEmpty()) {
            return ValidationResult.fail("That's as empty as my sense of direction. " +
                    "Give me the name of the item you're looking for, I'm a bounty hunter after all.");
        }

        return ValidationResult.success();
    }



    //nested public class that holds the results of a validations
    public static class ValidationResult {
        //private class to hold validation results
        private final boolean isValid;
        private final String errorMessage;

        private ValidationResult(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }

        public static ValidationResult success() {
            return new ValidationResult(true, null);
        }

        public static ValidationResult fail(String errorMessage) {
            return new ValidationResult(false, errorMessage);
        }

        public boolean isValid() {
            return isValid;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
