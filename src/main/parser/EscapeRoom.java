package main.parser;

public class EscapeRoom {
    private String name;
    private String company;
    private String location;
    private String translation;

    public EscapeRoom(EscapeRoomBuilder builder) {
        this.name = builder.name;
        this.company = builder.company;
        this.location = builder.location;
        this.translation = builder.translation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    
    public static class EscapeRoomBuilder {
        private String name;
        private String company;
        private String location;
        private String translation;

        public static EscapeRoomBuilder newInstance() {
            return new EscapeRoomBuilder();
        }

        private EscapeRoomBuilder() {}

        public EscapeRoomBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EscapeRoomBuilder setCompany(String company) {
            this.company = company;
            return this;
        }

        public EscapeRoomBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public EscapeRoomBuilder setTranslation(String translation) {
            this.translation = translation;
            return this;
        }

        public EscapeRoom build() {
            return new EscapeRoom(this);
        }
    }
}
