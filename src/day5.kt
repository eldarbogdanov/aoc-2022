/*
        [G]         [D]     [Q]    
[P]     [T]         [L] [M] [Z]    
[Z] [Z] [C]         [Z] [G] [W]    
[M] [B] [F]         [P] [C] [H] [N]
[T] [S] [R]     [H] [W] [R] [L] [W]
[R] [T] [Q] [Z] [R] [S] [Z] [F] [P]
[C] [N] [H] [R] [N] [H] [D] [J] [Q]
[N] [D] [M] [G] [Z] [F] [W] [S] [S]
 1   2   3   4   5   6   7   8   9 

 */
val crates = mutableListOf("NCRTMZP", "DNTSBZ", "MHQRFCTG", "GRZ", "ZNRH", "FHSWPZLD", "WDZRCGM", "SJFLHWZQ", "SQPWN");
fun main() {
    val input = """..."""

    for(s in input.split("\n")) {
		val num = Integer.parseInt(s.split(" ")[1]);
        val from = Integer.parseInt(s.split(" ")[3]) - 1;
        val to = Integer.parseInt(s.split(" ")[5]) - 1;
        val len = crates[from].length;
        val x = crates[from].substring(len - num, len);
        val y = x;
        crates[from] = crates[from].substring(0, len - num);
        crates[to] = crates[to] + y;
    }
	println(crates);
}

// [PHPSFZRR, T, LG, NZBNCSFHGZSMQQW, DZCSWTZMFMZGZ, NRT, HWRQDCNWJRH, PL, D]
//RTGWZTHLD
//
// [GQHRNSZS, T, TH, PDZPLQMCFGWRMRG, LFSNBDCZDCWTR, JHZ, SQZWZNWNMHZ, PF, R]
// STHGRZZFR
