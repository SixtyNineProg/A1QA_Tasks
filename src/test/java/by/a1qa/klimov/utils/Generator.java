package by.a1qa.klimov.utils;

public class Generator {
    public static String generatePassword(
            int numCapitalLetter,
            int numLowerCaseLetter,
            int numNumeral,
            boolean atSymbol) {
        String password =
                //ASCII A(65)-Z(90)
                Randomizer.generateRandomText(numCapitalLetter, 65, 90) +
                        //ASCII a(97)-z(122)
                        Randomizer.generateRandomText(numLowerCaseLetter, 97, 122) +
                        //ASCII 0(48)-9(57)
                        Randomizer.generateRandomText(numNumeral, 48, 57);
        if (atSymbol) password += "@";
        return password;
    }
}
