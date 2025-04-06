package Streams.realObjects;

import java.util.List;

public class Experience {
    private String companyName;
    private int experience;
    private Tech techStack;
    public Experience(String companyName, int experience, Tech techStack) {
        this.companyName = companyName;
        this.experience = experience;
        this.techStack = techStack;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Tech getTechStack() {
        return techStack;
    }

    public void setTechStack(Tech techStack) {
        this.techStack = techStack;
    }
    @Override
    public String toString() {
        return companyName + ", " + experience + ", " + techStack;
    }
}
