package analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NOT_ACCEPT,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"46:9,25,44,46:2,44,46:18,25,19,46,51,52,7,52,46,23,24,5,3,20,4,47,6,49:10,1" +
",46,17,2,18,46,51,12,53,33,14,53,43,26,53,50,53:4,13,15,53:2,16,53,45,53:6," +
"46:3,8,46:2,28,42,32,35,36,54,11,54,31,54:2,9,29,34,10,39,54,27,37,30,40,41" +
",54:3,38,21,46,22,46:83,48,46:31,48,46:65294,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,151,
"0,1,2,3,1,4,1:4,5,6,1:8,7,8,9,1:8,10,1:3,10:5,11,10,1,10:2,1,12,10,13,14,15" +
",16,17,10,18,19,20,21,22,23,24,25,26,27,16,28,29,30,31,32,33,34,35,36,37,38" +
",39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63" +
",64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,12,84,85,86,87" +
",88,89,90,91,92,93,10,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108" +
",109,110,111")[0];

	private int yy_nxt[][] = unpackFromString(112,55,
"1,2,3,4,5,6,7,8,9,10,132,143,11,50:2,54,57,12,13,14,15,16,17,18,19,20,60,13" +
"2,145,146,132:3,63,132:3,147,132,148,132,149,132,66,21,69,14:3,22,72,23,24," +
"50,132,-1:57,25,-1:71,26,-1:39,27,-1:13,28,-1:45,132,49,132:6,-1:9,132:18,-" +
"1,132,-1:2,132,53,132,-1:2,132:2,-1:13,48,-1:35,50,-1:30,20,-1:73,30,-1:57," +
"64,-1,22,-1:14,132:8,-1:9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1,40:43" +
",-1,40:10,-1,121:46,46,121:7,-1:14,32,-1:49,132:2,31,132:5,-1:9,132:18,-1,1" +
"32,-1:2,132,53,132,-1:2,132:2,-1:49,50,-1:54,51,-1:33,70,-1:42,29,-1:32,50," +
"-1:34,75,-1:4,77,-1:29,132:8,-1:9,132:8,78,132:9,-1,132,-1:2,132,53,132,-1:" +
"2,132:2,-1:36,52,-1:12,50,-1:39,33,-1:29,132:2,80,132:5,-1:9,132:18,-1,132," +
"-1:2,132,53,132,-1:2,132:2,-1:27,133,-1:21,50,-1:35,79,-1:33,132:8,-1:9,132" +
":4,82,132:13,-1,132,-1:2,132,53,132,-1:2,132:2,-1:10,55,-1:38,50,-1:14,132:" +
"8,-1:9,132:3,84,132:14,-1,132,-1:2,132,53,132,-1:2,132:2,-1:31,58,-1:17,50," +
"-1:35,34,-1:33,132:8,-1:9,132:11,86,132:6,-1,132,-1:2,132,53,132,-1:2,132:2" +
",-1:31,61,-1:17,50,-1:14,34,-1:54,132,135,132:6,-1:9,132:18,-1,132,-1:2,132" +
",53,132,-1:2,132:2,-1:34,67,-1:14,50,-1:34,81,-1:34,132:8,-1:9,132,88,132:9" +
",90,132:6,-1,132,-1:2,132,53,132,-1:2,132:2,-1:36,83,-1:27,139,132:7,-1:9,1" +
"32:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:35,85,-1:28,132:8,-1:9,132:10,13" +
"4,132:7,-1,132,-1:2,132,53,132,-1:2,132:2,-1:40,87,-1:23,132:8,-1:9,132:14," +
"92,132:3,-1,132,-1:2,132,53,132,-1:2,132:2,-1:28,89,-1:35,132:8,-1:9,132,13" +
"6,132:16,-1,132,-1:2,132,53,132,-1:2,132:2,-1:34,138,-1:29,132:8,-1:9,132:1" +
"6,144,132,-1,132,-1:2,132,53,132,-1:2,132:2,-1:31,91,-1:32,132:8,-1:9,132:5" +
",94,132:12,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,93,-1:54,132:8,-1:9,132:2" +
",96,132:15,-1,132,-1:2,132,53,132,-1:2,132:2,-1:30,95,-1:33,132,98,132:6,-1" +
":9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:32,140,-1:31,104,132:7,-1:9," +
"132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:10,99,-1:53,132:8,-1:9,132:6,11" +
"0,132:11,-1,132,-1:2,132,53,132,-1:2,132:2,-1:31,101,-1:32,132:8,-1:9,132:3" +
",114,132:14,-1,132,-1:2,132,53,132,-1:2,132:2,-1:28,103,-1:35,132:8,-1:9,13" +
"2:11,35,132:6,-1,132,-1:2,132,53,132,-1:2,132:2,-1,40,-1:62,132:8,-1:9,132," +
"36,132:16,-1,132,-1:2,132,53,132,-1:2,132:2,-1:32,107,-1:31,132:8,-1:9,132:" +
"2,116,132:15,-1,132,-1:2,132,53,132,-1:2,132:2,-1:27,109,-1:36,132,37,132:6" +
",-1:9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:10,111,-1:53,132:8,-1:9,1" +
"32:12,38,132:5,-1,132,-1:2,132,53,132,-1:2,132:2,-1:28,42,-1:35,118,132:7,-" +
"1:9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:31,113,-1:32,132:8,-1:9,132" +
":5,120,132:12,-1,132,-1:2,132,53,132,-1:2,132:2,-1:34,115,-1:29,132:8,-1:9," +
"132:16,122,132,-1,132,-1:2,132,53,132,-1:2,132:2,-1:10,117,-1:53,132:8,-1:9" +
",132:10,150,132:7,-1,132,-1:2,132,53,132,-1:2,132:2,-1:36,119,-1:27,132:8,-" +
"1:9,132:4,123,132:13,-1,132,-1:2,132,53,132,-1:2,132:2,-1:37,121,-1:26,132," +
"39,132:6,-1:9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:37,45,-1:26,132,1" +
"24,132:6,-1:9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:" +
"5,125,132:12,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132,126,132:" +
"16,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:8,41,132:9,-1,132," +
"-1:2,132,53,132,-1:2,132:2,-1:9,142,132:7,-1:9,132:18,-1,132,-1:2,132,53,13" +
"2,-1:2,132:2,-1:9,132:8,-1:9,132:5,128,132:12,-1,132,-1:2,132,53,132,-1:2,1" +
"32:2,-1:9,132,43,132:6,-1:9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,1" +
"32:8,-1:9,132:12,44,132:5,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9" +
",132:9,130,132:8,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:2,13" +
"1,132:15,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:9,47,132:8,-" +
"1,132,-1:2,132,53,132,-1:2,132:2,-1:28,73,-1:35,132:8,-1:9,132,102,132:16,-" +
"1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:16,137,132,-1,132,-1:2" +
",132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:5,106,132:12,-1,132,-1:2,132,53," +
"132,-1:2,132:2,-1:9,132:8,-1:9,132:2,112,132:15,-1,132,-1:2,132,53,132,-1:2" +
",132:2,-1:30,97,-1:33,132,100,132:6,-1:9,132:18,-1,132,-1:2,132,53,132,-1:2" +
",132:2,-1:31,105,-1:32,132:8,-1:9,132,127,132:16,-1,132,-1:2,132,53,132,-1:" +
"2,132:2,-1:9,132:8,-1:9,132:5,129,132:12,-1,132,-1:2,132,53,132,-1:2,132:2," +
"-1:9,132:8,-1:9,132:10,56,132:7,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132," +
"108,132:6,-1:9,132:18,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132" +
":8,59,132:9,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:2,62,132:" +
"15,-1,132,-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:5,65,132:12,-1,132" +
",-1:2,132,53,132,-1:2,132:2,-1:9,132,68,132:6,-1:9,132,71,74,132:15,-1,132," +
"-1:2,132,53,132,-1:2,132:2,-1:9,132:8,-1:9,132:2,76,132:15,-1,132,-1:2,132," +
"53,132,-1:2,132:2,-1:9,132:8,-1:9,132:4,141,132:13,-1,132,-1:2,132,53,132,-" +
"1:2,132:2");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

    {System.out.printf("FIN DE ARCHIVO\n");}
    {System.exit(0); }
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{return new Symbol(sym.DOSPTS,yyline, yychar, yytext());}
					case -3:
						break;
					case 3:
						{System.out.println("IGUAL"); return new Symbol(sym.OP_IGUAL,yyline, yychar, yytext());}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.OP_SUMA,yyline, yychar, yytext());}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.OP_RESTA,yyline, yychar, yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.OP_PRODUCTO,yyline, yychar, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.OP_DIVISION,yyline, yychar, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.OP_MOD,yyline, yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.OP_POTENCIA,yyline, yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.OP_MENOR,yyline, yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.OP_MAYOR,yyline, yychar, yytext());}
					case -14:
						break;
					case 14:
						{System.out.println("Este es un error lexico: "+yytext()+    ", en la linea: "+yyline+", en la columna: "+yychar); System.exit(0);}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.COMA,yyline, yychar, yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.PUSH,yyline, yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.POP,yyline, yychar, yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.LP,yyline, yychar, yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.RP,yyline, yychar, yytext());}
					case -20:
						break;
					case 20:
						{}
					case -21:
						break;
					case 21:
						{yychar=1;}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.NUMERO, yyline, yychar, new Float(yytext()));}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.MOV,yyline, yychar, yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.TURN,yyline, yychar, yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.OP_ASSIGN,yyline, yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.OP_DIF,yyline, yychar, yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.DOBLERAYA,yyline, yychar, yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.FLECHA,yyline, yychar, yytext());}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.OP_OR,yyline, yychar, yytext());}
					case -30:
						break;
					case 30:
						{yychar=1;return new Symbol(sym.DOBLESALTO,yyline, yychar, yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.OP_LOG,yyline, yychar, yytext());}
					case -32:
						break;
					case 32:
						{System.out.println("encontre un and"); return new Symbol(sym.OP_AND,yyline, yychar, yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.FIN,yyline, yychar, yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.TYPE,yyline, yychar, yytext());}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.PASOS,yyline, yychar, yytext());}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.VALOR,yyline, yychar, yytext());}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.ANGULO,yyline, yychar, yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.MATRIZ,yyline, yychar, yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.SIMBOLO,yyline, yychar, yytext());}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.TITULO,yyline, yychar, yytext().substring(7,yytext().length()-1));}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.POSICION,yyline, yychar, yytext());}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.GRAMATICA,yyline, yychar, yytext());}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.PARAMETRO,yyline, yychar, yytext());}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.GENERATRIZ,yyline, yychar, yytext());}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.CONDICIONES,yyline, yychar, yytext());}
					case -46:
						break;
					case 46:
						{System.out.println(yytext()); return new Symbol(sym.COMENTARIOS,yyline, yychar, yytext());}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.PROBABILIDAD,yyline, yychar, yytext());}
					case -48:
						break;
					case 49:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -49:
						break;
					case 50:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -50:
						break;
					case 51:
						{return new Symbol(sym.NUMERO, yyline, yychar, new Float(yytext()));}
					case -51:
						break;
					case 53:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -52:
						break;
					case 54:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -53:
						break;
					case 56:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -54:
						break;
					case 57:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -55:
						break;
					case 59:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -56:
						break;
					case 60:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -57:
						break;
					case 62:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -58:
						break;
					case 63:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -59:
						break;
					case 65:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -60:
						break;
					case 66:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -61:
						break;
					case 68:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -62:
						break;
					case 69:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -63:
						break;
					case 71:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -64:
						break;
					case 72:
						{return new Symbol(sym.NOM_SIMBOL,yyline, yychar, yytext());}
					case -65:
						break;
					case 74:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -66:
						break;
					case 76:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -67:
						break;
					case 78:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -68:
						break;
					case 80:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -69:
						break;
					case 82:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -70:
						break;
					case 84:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -71:
						break;
					case 86:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -72:
						break;
					case 88:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -73:
						break;
					case 90:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -74:
						break;
					case 92:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -75:
						break;
					case 94:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -76:
						break;
					case 96:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -77:
						break;
					case 98:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -78:
						break;
					case 100:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -79:
						break;
					case 102:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -80:
						break;
					case 104:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -81:
						break;
					case 106:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -82:
						break;
					case 108:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -83:
						break;
					case 110:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -84:
						break;
					case 112:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -85:
						break;
					case 114:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -86:
						break;
					case 116:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -87:
						break;
					case 118:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -88:
						break;
					case 120:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -89:
						break;
					case 122:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -90:
						break;
					case 123:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -91:
						break;
					case 124:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -92:
						break;
					case 125:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -93:
						break;
					case 126:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -94:
						break;
					case 127:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -95:
						break;
					case 128:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -96:
						break;
					case 129:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -97:
						break;
					case 130:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -98:
						break;
					case 131:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -99:
						break;
					case 132:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -100:
						break;
					case 134:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -101:
						break;
					case 135:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -102:
						break;
					case 136:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -103:
						break;
					case 137:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -104:
						break;
					case 139:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -105:
						break;
					case 141:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -106:
						break;
					case 142:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -107:
						break;
					case 143:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -108:
						break;
					case 144:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -109:
						break;
					case 145:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -110:
						break;
					case 146:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -111:
						break;
					case 147:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -112:
						break;
					case 148:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -113:
						break;
					case 149:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -114:
						break;
					case 150:
						{return new Symbol(sym.NOM_PARAM,yyline, yychar, yytext());}
					case -115:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
