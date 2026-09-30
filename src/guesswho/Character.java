package guesswho;

public class Character {
    private String name, hairColour, eyeColour, gender, facialHair, hairStyle, clothing;
    private boolean glasses;

    public Character(String name, String hairColour, String eyeColour, String gender,
            String facialHair, String hairStyle, String clothing, boolean glasses) {
        this.name = name;
        this.hairColour = hairColour;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.facialHair = facialHair;
        this.hairStyle = hairStyle;
        this.clothing = clothing;
        this.glasses = glasses;
    }

    public String getName() {
        return name;
    }

    public String getHairColour() {
        return hairColour;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public String getFacialHair() {
        return facialHair;
    }

    public String getHairStyle() {
        return hairStyle;
    }

    public String getClothing() {
        return clothing;
    }

    public boolean hasGlasses() {
        return glasses;
    }

    public boolean matches(Question q) {
        String attr = q.getAttribute().toLowerCase();
        String val = q.getValue().toString().toLowerCase();

        if (attr.equals("name")) {
            return name.equalsIgnoreCase(val);
        } else if (attr.equals("hair colour")) {
            return hairColour.equalsIgnoreCase(val);
        } else if (attr.equals("eye colour")) {
            return eyeColour.equalsIgnoreCase(val);
        } else if (attr.equals("gender")) {
            return gender.equalsIgnoreCase(val);
        } else if (attr.equals("facial hair")) {
            return facialHair.equalsIgnoreCase(val);
        } else if (attr.equals("hair style")) {
            return hairStyle.equalsIgnoreCase(val);
        } else if (attr.equals("clothing")) {
            return clothing.equalsIgnoreCase(val);
        } else if (attr.equals("glasses")) {
            return Boolean.toString(glasses).equalsIgnoreCase(val);
        } else {
            return false;
        }
    }
}
