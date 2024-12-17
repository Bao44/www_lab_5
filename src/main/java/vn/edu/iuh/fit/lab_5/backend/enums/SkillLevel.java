package vn.edu.iuh.fit.lab_5.backend.enums;

public enum SkillLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, PROFESSIONAL, MASTER;

    public static SkillLevel fromString(String level) {
        switch (level.toUpperCase()) {
            case "BEGINNER": return BEGINNER;
            case "INTERMEDIATE": return INTERMEDIATE;
            case "ADVANCED": return ADVANCED;
            case "PROFESSIONAL": return PROFESSIONAL;
            case "MASTER": return MASTER;
            default: throw new IllegalArgumentException("Unknown skill level: " + level);
        }
    }
}

