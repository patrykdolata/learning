import java.io.BufferedReader;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    /*
        John keeps a backup of his old personal phone book as a text file.
        On each line of the file he can find the phone number
        (formated as +X-abc-def-ghij where X stands for one or two digits),
        the corresponding name between < and > and the address.
        Unfortunately everything is mixed, things are not always in the same order;
        parts of lines are cluttered with non-alpha-numeric characters
        (except inside phone number and name).
        Examples of John's phone book lines:
        "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n"
        " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010!\n"
        "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n"
        Could you help John with a program that, given the lines of his phone book
        and a phone number returns a string for this number :
        "Phone => num, Name => name, Address => adress"
     */
    public static void main(String[] args) {
        String phoneBook = "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
                " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
                "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
                " :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
                "+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
                " :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
                "+1-111-544-8973 <Peter Pan> LA\n" +
                " +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
                "<Peter Gone> LA ?+1-121-544-8974 \n" +
                " <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
                "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n" +
                " <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
                "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
                " <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
                "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
                " <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
                "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
                " <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
                "+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
                " +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
                " 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
                "+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
                " :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
                "+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
                " :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
                "+8-111-544-8973 <Laurence Pantow> SA\n" +
                " +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
                "<John Freeland> Mantow ?+2-121-544-8974 \n" +
                " <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
                "<Arthur Paternos> San Antonio $+7-121-504-8974 TT-45121\n" +
                " <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
                "<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
                " <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
                "<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
                " <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
                "<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
                " <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
                "+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
                " +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
                "<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville\n" +
                "\n" +
                "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
                " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
                "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
                " :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
                "+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
                " :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
                "+1-111-544-8973 <Peter Pan> LA\n" +
                " +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
                "<Peter Gone> LA ?+1-121-544-8974 \n" +
                " <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
                "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n" +
                " <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
                "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
                " <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
                "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
                " <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
                "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
                " <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
                "+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
                " +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
                " 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
                "+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
                " :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
                "+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
                " :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
                "+8-111-544-8973 <Laurence Pantow> SA\n" +
                " +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
                "<John Freeland> Mantow ?+2-121-544-8974 \n" +
                " <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
                "<Arthur Paternos> San Antonio $+7-121-504-8974 TT-45121\n" +
                " <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
                "<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
                " <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
                "<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
                " <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
                "<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
                " <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
                "+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
                " +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
                "<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville\n" +
                "\n" +
                "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
                " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
                "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
                " :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
                "+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
                " :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
                "+1-111-544-8973 <Peter Pan> LA\n" +
                " +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
                "<Peter Gone> LA ?+1-121-544-8974 \n" +
                " <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
                "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n" +
                " <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
                "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
                " <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
                "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
                " <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
                "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
                " <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
                "+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
                " +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
                " 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
                "+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
                " :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
                "+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
                " :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
                "+8-111-544-8973 <Laurence Pantow> SA\n" +
                " +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
                "<John Freeland> Mantow ?+2-121-544-8974 \n" +
                " <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
                "<Arthur Paternos> San Antonio $+7-121-504-8974 TT-45121\n" +
                " <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
                "<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
                " <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
                "<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
                " <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
                "<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
                " <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
                "+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
                " +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
                "<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville\n" +
                "\n" +
                "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
                " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
                "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
                " :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
                "+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
                " :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
                "+1-111-544-8973 <Peter Pan> LA\n" +
                " +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
                "<Peter Gone> LA ?+1-121-544-8974 \n" +
                " <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
                "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n" +
                " <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
                "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
                " <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
                "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
                " <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
                "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
                " <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
                "+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
                " +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
                " 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
                "+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
                " :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
                "+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
                " :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
                "+8-111-544-8973 <Laurence Pantow> SA\n" +
                " +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
                "<John Freeland> Mantow ?+2-121-544-8974 \n" +
                " <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
                "<Arthur Paternos> San Antonio $+7-121-504-8974 TT-45121\n" +
                " <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
                "<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
                " <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
                "<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
                " <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
                "<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
                " <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
                "+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
                " +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
                "<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville\n" +
                "\n" +
                "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
                " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
                "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
                " :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
                "+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
                " :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
                "+1-111-544-8973 <Peter Pan> LA\n" +
                " +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
                "<Peter Gone> LA ?+1-121-544-8974 \n" +
                " <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
                "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n" +
                " <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
                "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
                " <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
                "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
                " <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
                "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
                " <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
                "+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
                " +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
                " 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
                "+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
                " :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
                "+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
                " :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
                "+8-111-544-8973 <Laurence Pantow> SA\n" +
                " +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
                "<John Freeland> Mantow ?+2-121-544-8974 \n" +
                " <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
                "<Arthur Paternos> San Antonio $+7-121-504-8974 TT-45121\n" +
                " <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
                "<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
                " <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
                "<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
                " <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
                "<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
                " <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
                "+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
                " +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
                "<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville\n" +
                "\n" +
                "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
                " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
                "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
                " :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
                "+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
                " :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
                "+1-111-544-8973 <Peter Pan> LA\n" +
                " +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
                "<Peter Gone> LA ?+1-121-544-8974 \n" +
                " <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
                "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n" +
                " <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
                "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
                " <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
                "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
                " <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
                "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
                " <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
                "+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
                " +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
                " 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
                "+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
                " :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
                "+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
                " :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
                "+8-111-544-8973 <Laurence Pantow> SA\n" +
                " +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
                "<John Freeland> Mantow ?+2-121-544-8974 \n" +
                " <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
                "<Arthur Paternos> San Antonio $+7-121-504-8974 TT-45121\n" +
                " <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
                "<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
                " <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
                "<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
                " <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
                "<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
                " <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
                "+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
                " +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
                "<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville\n" +
                "\n" +
                "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
                " 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
                "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
                " :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
                "+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
                " :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
                "+1-111-544-8973 <Peter Pan> LA\n" +
                " +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
                "<Peter Gone> LA ?+1-121-544-8974 \n" +
                " <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
                "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n" +
                " <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
                "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
                " <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
                "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
                " <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
                "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
                " <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
                "+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
                " +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
                "/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
                " 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
                "+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
                " :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
                "+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
                " :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
                "+8-111-544-8973 <Laurence Pantow> SA\n" +
                " +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
                "<John Freeland> Mantow ?+2-121-544-8974 \n" +
                " <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
                "<Arthur Paternos> San Antonio $+7-121-504-8974 TT-45121\n" +
                " <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
                "<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
                " <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
                "<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
                " <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
                "<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
                " <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
                "+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
                " +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
                "<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville";
        String phoneNumber = "8-421-674-8974";
        System.out.println(phone(phoneBook, phoneNumber));
    }

    private static String phone(String phoneBook, String phoneNumber) {
        BufferedReader reader = new BufferedReader(new StringReader(phoneBook));
        List<Person> data = reader.lines().map(Main::parseLine).collect(Collectors.toList());
        List<Person> filteredData = data.stream().filter(person -> person.phoneNumber.equals(phoneNumber)).collect(Collectors.toList());
        if (filteredData.size() == 0) {
            return "Error => Not found: " + phoneNumber;
        }
        return checkDuplicate(filteredData,phoneNumber);
    }

    private static String checkDuplicate(List<Person> filteredData, String phoneNumber) {
        Person first = filteredData.get(0);
        for (Person person : filteredData) {
            if (!person.name.equals(first.name)) {
                return "Error => Too many people: " + phoneNumber;
            }
        }
        return first.toString();
    }

    private static Person parseLine(String line) {
        Pattern namePattern = Pattern.compile("<.*>");
        Pattern phonePattern = Pattern.compile("\\+[0-9]{1,2}-\\d{3}-\\d{3}-\\d{4}");
        Matcher nameMatcher = namePattern.matcher(line);
        Matcher phoneMatcher = phonePattern.matcher(line);
        String name = "";
        String phoneNumber = "";
        if (nameMatcher.find()) {
            name = nameMatcher.group().replaceAll("<", "")
                    .replaceAll(">", "");
        }
        if (phoneMatcher.find()) {
            phoneNumber = phoneMatcher.group();
            phoneNumber = phoneNumber.replace("+", "");
        }
        String address = line.replaceAll(namePattern.toString(), "")
                .replaceAll(",", "")
                .replaceAll("_St", " St")
                .replaceAll("", "")
                .replaceAll(".?\\+[0-9]{1,2}-\\d{3}-\\d{3}-\\d{4}.?", "")
                .replaceAll(" +", " ")
                .trim();
        return new Person(name, address, phoneNumber);
    }


    static class Person {
        public String name;
        public String address;
        public String phoneNumber;

        public Person(String name, String address, String phoneNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "Phone => " + phoneNumber + ", Name => " + name + ", Address => " + address;
        }
    }
}