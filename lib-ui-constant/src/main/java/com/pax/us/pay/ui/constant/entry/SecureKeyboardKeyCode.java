package com.pax.us.pay.ui.constant.entry;

public class SecureKeyboardKeyCode {

    public static final String KEYCODE_0 = "0";
    public static final String KEYCODE_1 = "1";
    public static final String KEYCODE_2 = "2";
    public static final String KEYCODE_3 = "3";
    public static final String KEYCODE_4 = "4";
    public static final String KEYCODE_5 = "5";
    public static final String KEYCODE_6 = "6";
    public static final String KEYCODE_7 = "7";
    public static final String KEYCODE_8 = "8";
    public static final String KEYCODE_9 = "9";

    public static final String KEYCODE_ENTER = "ENTER";
    public static final String KEYCODE_CLEAR = "CLEAR";
    public static final String KEYCODE_DELETE = "DELETE";
    public static final String KEYCODE_BACK = "BACK";
    public static final String KEYCODE_ESCAPE = "ESCAPE";

    public static final String KEYCODE_a = "a";
    public static final String KEYCODE_b = "b";
    public static final String KEYCODE_c = "c";
    public static final String KEYCODE_d = "d";
    public static final String KEYCODE_e = "e";
    public static final String KEYCODE_f = "f";
    public static final String KEYCODE_g = "g";
    public static final String KEYCODE_h = "h";
    public static final String KEYCODE_i = "i";
    public static final String KEYCODE_j = "j";
    public static final String KEYCODE_k = "k";
    public static final String KEYCODE_l = "l";
    public static final String KEYCODE_m = "m";
    public static final String KEYCODE_n = "n";
    public static final String KEYCODE_o = "o";
    public static final String KEYCODE_p = "p";
    public static final String KEYCODE_q = "q";
    public static final String KEYCODE_r = "r";
    public static final String KEYCODE_s = "s";
    public static final String KEYCODE_t = "t";
    public static final String KEYCODE_u = "u";
    public static final String KEYCODE_v = "v";
    public static final String KEYCODE_w = "w";
    public static final String KEYCODE_x = "x";
    public static final String KEYCODE_y = "y";
    public static final String KEYCODE_z = "z";

    public static final String KEYCODE_A = "A";
    public static final String KEYCODE_B = "B";
    public static final String KEYCODE_C = "C";
    public static final String KEYCODE_D = "D";
    public static final String KEYCODE_E = "E";
    public static final String KEYCODE_F = "F";
    public static final String KEYCODE_G = "G";
    public static final String KEYCODE_H = "H";
    public static final String KEYCODE_I = "I";
    public static final String KEYCODE_J = "J";
    public static final String KEYCODE_K = "K";
    public static final String KEYCODE_L = "L";
    public static final String KEYCODE_M = "M";
    public static final String KEYCODE_N = "N";
    public static final String KEYCODE_O = "O";
    public static final String KEYCODE_P = "P";
    public static final String KEYCODE_Q = "Q";
    public static final String KEYCODE_R = "R";
    public static final String KEYCODE_S = "S";
    public static final String KEYCODE_T = "T";
    public static final String KEYCODE_U = "U";
    public static final String KEYCODE_V = "V";
    public static final String KEYCODE_W = "W";
    public static final String KEYCODE_X = "X";
    public static final String KEYCODE_Y = "Y";
    public static final String KEYCODE_Z = "Z";

    public static final String KEYCODE_COMMA = "COMMA";
    public static final String KEYCODE_DOT = "DOT";
    public static final String KEYCODE_SPACE = "SPACE";
    public static final String KEYCODE_LESS_THAN = "LESS_THAN";
    public static final String KEYCODE_GREATER_THAN = "GREATER_THAN";
    public static final String KEYCODE_COLON = "COLON";
    public static final String KEYCODE_SEMICOLON = "SEMICOLON";
    public static final String KEYCODE_LEFT_PARENTHESES = "LEFT_PARENTHESES";
    public static final String KEYCODE_RIGHT_PARENTHESES = "RIGHT_PARENTHESES";
    public static final String KEYCODE_DOLLAR = "DOLLAR";
    public static final String KEYCODE_AMPERSAND = "AMPERSAND";
    public static final String KEYCODE_AT = "AT";
    public static final String KEYCODE_DOUBLE_QUOTE = "DOUBLE_QUOTE";
    public static final String KEYCODE_SINGLE_QUOTE = "SINGLE_QUOTE";
    public static final String KEYCODE_LEFT_BRACKET = "LEFT_BRACKET";
    public static final String KEYCODE_RIGHT_BRACKET = "RIGHT_BRACKET";
    public static final String KEYCODE_LEFT_BRACE = "LEFT_BRACE";
    public static final String KEYCODE_RIGHT_BRACE = "RIGHT_BRACE";
    public static final String KEYCODE_HASH = "HASH";
    public static final String KEYCODE_PERCENT = "PERCENT";
    public static final String KEYCODE_CARET = "CARET";
    public static final String KEYCODE_STAR = "STAR";
    public static final String KEYCODE_PLUS = "PLUS";
    public static final String KEYCODE_EQUAL = "EQUAL";
    public static final String KEYCODE_BACKSLASH = "BACKSLASH";
    public static final String KEYCODE_SLASH = "SLASH";
    public static final String KEYCODE_TILDE = "TILDE";
    public static final String KEYCODE_GRAVE = "GRAVE";
    public static final String KEYCODE_QUESTION_MARK = "QUESTION_MARK";
    public static final String KEYCODE_EXCLAMATION_MARK = "EXCLAMATION_MARK";
    public static final String KEYCODE_PIPE = "PIPE";
    public static final String KEYCODE_UNDERSCORE = "UNDERSCORE";
    public static final String KEYCODE_MINUS = "MINUS";

    /**
     * This is a special key code for communication between the UI App and BroadPOS.
     * When BroadPOS receives this key code, it will deactivate the secure keyboard and send back the {@link EntryRequest#PARAM_SECURE_KEYBOARD_PAYLOAD} to the UI App.
     */
    public static final String KEYCODE_DEACTIVATE_SECURE_KEYBOARD = "DEACTIVATE_SECURE_KEYBOARD";

}
