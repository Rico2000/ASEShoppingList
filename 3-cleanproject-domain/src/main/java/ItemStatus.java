public enum ItemStatus {
    GEKAUFT("Gekauft"),
    OFFEN("offen");
    private String text;

    ItemStatus(String text) {
        this.text = text;
    }



    public String getText() {
        return this.text;
    }

    public static ItemStatus fromString(String text) {
        for (ItemStatus b : ItemStatus.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
