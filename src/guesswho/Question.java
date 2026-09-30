package guesswho;

public class Question {
    private String attribute;
    private Object value;

    public Question(String attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    public boolean matches(Character character) {
        if (attribute.equals("hairColour")) {
            return character.getHairColour().equals(value);
        } else if (attribute.equals("eyeColour")) {
            return character.getEyeColour().equals(value);
        } else if (attribute.equals("gender")) {
            return character.getGender().equals(value);
        } else if (attribute.equals("facialHair")) {
            return character.getFacialHair().equals(value);
        } else if (attribute.equals("hairStyle")) {
            return character.getHairStyle().equals(value);
        } else if (attribute.equals("clothing")) {
            return character.getClothing().equals(value);
        } else if (attribute.equals("glasses")) {
            return character.hasGlasses() == (boolean) value;
        } else {
            return false;
        }
    }

    public String getAttribute() {
        return attribute;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        return "Does your character have " + attribute + " = " + value + "?";
    }
}
