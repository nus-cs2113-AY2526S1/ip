public class Event extends Task {

    protected String from;
    protected String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    public static Event getEvent(String[] sentenceArray, String sentence) throws BobException {
        if (sentenceArray.length < 2 || sentenceArray[1].isEmpty()) {
            throw new BobException("Description of a event cannot be empty - Bob 😡");
        }
        int firstSpaceIndex = sentence.indexOf(" ");
        int fromIndex = sentence.indexOf("/from");
        int toIndex = sentence.indexOf("/to");

        String eventTask = sentence.substring(firstSpaceIndex + 1, fromIndex).trim();
        String from = sentence.substring(fromIndex + 5, toIndex).trim();
        String to = sentence.substring(toIndex + 3).trim();

        return new Event(eventTask, from, to);
    }

}
