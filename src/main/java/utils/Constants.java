package utils;

//use ordinal to return the position of the constant in the enum.

public enum Constants {

	BEGINNER(1),
	FACULTY(1),
	STUDENT(2);
	

    private int value;

    Constants(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}