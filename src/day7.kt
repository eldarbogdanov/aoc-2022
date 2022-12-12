class Node(val name: String, val folders: MutableMap<String, Node>, val files: MutableMap<String, Long>);
val parents: MutableMap<Node, Node> = mutableMapOf<Node, Node>();
val folderSizes: MutableMap<Node, Long> = mutableMapOf();

fun sumFiles(cur: Node, limit: Long): Pair<Long, Long> {
    var size: Long = cur.files.values.sum();
    var ret: Long = 0;
	for(subfolder in cur.folders.keys) {
		val (folderSize, ans) = sumFiles(cur.folders[subfolder]!!, limit);
        size += folderSize;
        ret += ans;
    }
    folderSizes.put(cur, size);
    if (size <= limit) ret += size;
    return Pair(size, ret);
}

fun main() {
    val input = ""
    var root = Node("/", mutableMapOf<String, Node>(), mutableMapOf<String, Long>());
    var currentNode: Node? = null;
    for(s in input.split("\n")) {
        //println(s + " " + currentNode?.name);
        if (!s.startsWith("$")) {
            if (s.startsWith("dir")) {
                val subfolder = s.split(" ")[1];
                val newNode = Node(subfolder, mutableMapOf(), mutableMapOf());
                currentNode!!.folders.put(subfolder, newNode);
                parents.put(newNode, currentNode);
            } else {
                val fileName = s.split(" ")[1];
                val fileSize = Integer.parseInt(s.split(" ")[0]);
                currentNode!!.files.put(fileName, fileSize.toLong());
            }
        } else
		if (s == "$ cd /") currentNode = root; else
        if (s == "$ cd ..") currentNode = parents[currentNode!!]; else
        if (s.startsWith("$ cd")) {
            //println(currentNode!!.folders);
            currentNode = currentNode!!.folders[s.split(" ")[2]];
        } else {
            if (s != "$ ls") println("oops " + s);
        }
    }
    
    println(sumFiles(root, 100000).second);
    
    val totalSize = folderSizes[root]!!;
    var ans: Long = 1000000000;
    for(entry in folderSizes.entries.iterator()) {
        val size = entry.value;
        if (70000000 - totalSize + size >= 30000000 && size < ans) {
            ans = size;
        }
    }
    println(ans);
}

