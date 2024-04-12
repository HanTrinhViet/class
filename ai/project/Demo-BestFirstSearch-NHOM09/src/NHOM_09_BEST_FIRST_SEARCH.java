import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.StringTemplate.STR;


/**
 * @author Trịnh Viết Hân
 * @apiNote NHÓM 09
 * @implNote Tìm đường đi ngắn nhất trên đồ thị có trọng số sử dụng thuật toán Best First Search
 */
public class NHOM_09_BEST_FIRST_SEARCH {
    private static final Map<Node, List<Node>> nodeMap = new HashMap<>();
    private static final StringBuilder table =
            new StringBuilder("\t\t%-7s%-17s%-17s\n".formatted("PTTT", "Trạng thái kề", "Danh sách L"));


    public static void main(String[] args) {
        // Tạo đối tượng của lớp Scanner và gán bằng null
        Scanner in;
        try {
            // Khởi tạo đối tượng Scanner để đọc vào file 'input.txt'
            in = new Scanner(new File("input.txt"));

            // Đọc bỏ 15 dòng hướng dẫn nhập vào đầu tiên của file 'input.txt'
            for (int i = 1; i <= 15; i++) {
                in.nextLine();
            }

            while (in.hasNextLine()) {
                String line = in.nextLine();

                // Trường hợp đọc vào các đỉnh và trọng số
                if (!line.contains(">") && line.contains("-")) {
                    String[] data = line.split("\\s-\\s");

                    // Tách đỉnh đầu tiên và trọng số của nó
                    String top = data[0].substring(0, 1);
                    int weight = Integer.parseInt(data[0].substring(1));
                    Node firstNode = new Node(top, weight);

                    // Tách các đỉnh kề với đỉnh đầu tiên kèm trọng số của chúng nó, vd: ["E20", "B14", "D89"]
                    String[] adjNodes = data[1].split(",\\s");

                    // Xử lý từng đỉnh kề và thêm vào trong map với KEY là đỉnh đầu tiên
                    for (String node : adjNodes) {
                        String adjNodeTop = node.substring(0, 1);
                        int adjNodeWeight = Integer.parseInt(node.substring(1));
                        Node adjNode = new Node(adjNodeTop, adjNodeWeight);
                        addToNodeMap(firstNode, adjNode);
                    }
                }

                if (!line.contains("-") && !line.contains("->")) {
                    String symbol = line.substring(0, 1);
                    var lists = new ArrayList<Node>();
                    var keys = nodeMap.entrySet().stream().toList();
                    var values = nodeMap.values().stream().toList();


                    for (List<Node> list : values) {
                        for (Node node : list) {
                            if (symbol.equals(node.top)) {
                                System.err.println("Đỉnh trùng rồi !" + line);
                            }
                        }
                    }
                }


                // Kiểm tra để đọc vào trạng thái bắt đầu và trạng thái kết thúc
                if (line.contains("-") && line.contains(">")) {
                    String[] data = line.split("->");
                    String startNodeTop = data[0].trim().substring(0, 1);
                    int startNodeWeight = Integer.parseInt(data[0].trim().substring(1));
                    String endNodeTop = data[1].trim().substring(0, 1);
                    int endNodeWeight = Integer.parseInt(data[1].trim().substring(1));
                    Node startNode = new Node(startNodeTop, startNodeWeight);
                    Node endNode = new Node(endNodeTop, endNodeWeight);
                    List<Node> shortestPaths = findShortestPathBestFS(startNode, endNode);

                    // Tạo đối tượng của PrintWriter để ghi dữ liệu ra file 'output.txt'
                    PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

                    if (!shortestPaths.isEmpty()) {
                        // Hiển thị ra file 'output.txt' trạng thái đầu và trạng thái kết thúc
                        out.println("\t\t====> YỀU CẦU ĐẦU VÀO");
                        out.println(STR."\t\tTrạng thái đầu: \{startNode}");
                        out.println(STR."\t\tTrạng thái kết thúc: \{endNode}");

                        // Hiển thị ra file 'output.txt' bảng liệt kê trạng thái
                        out.println("\n\t\t===> BẢNG LIỆT KÊ TRẠNG THÁI");
                        out.println(table);

                        // Hiển thị ra file 'output.txt' đường đi từ trạng thái bắt đầu đến trạng thái kết thúc
                        out.println("\t\t===> KẾT QUẢ");
                        out.print("\t\t");
                        for (Node node : shortestPaths) {
                            out.print(STR."\{node}");
                            if (!node.equals(shortestPaths.getLast())) {
                                out.print(" --> ");
                            }
                        }
                    } else {
                        out.println("==> Lỗi không tìm được đường đi ngắn nhất! Vui lòng xem lại input! <==");
                    }

                    out.close(); // Đóng lại luồng ghi file
                }
            }
            in.close(); // Đóng lại luồng đọc file
        } catch (IOException ex) { // Trong trường hợp file không tồn tại -> ghi log ra lỗi
            System.out.println("File không tồn tại!");
        }
    }

    private static List<Node> findShortestPathBestFS(Node startNode, Node endNode) {
        List<Node> shortestPaths = new LinkedList<>(); // danh sách đường đi cần tìm từ đỉnh đầu đến đỉnh đích
        Queue<List<Node>> queue = new LinkedList<>();    // hàng đợi để chứa danh sách, mỗi danh sách là một đường đi
        queue.add(Collections.singletonList(startNode)); // tạo mới một danh sách chứa đỉnh bắt đầu và thêm vào hàng đợi

        // Vòng lặp while duyệt qua danh sách các đường đi trong queue
        while (!queue.isEmpty()) {
            List<Node> path = queue.poll(); // poll hay lấy ra phần tử (đường đi) ở đầu hàng đợi
            Node node = path.getLast(); // Lấy ra đỉnh cuối cùng đã thăm của đường đi này

            // Kiểm tra nếu như đỉnh cuối cùng đã thăm của đường đi mà trùng với đỉnh dích thì dừng
            if (node.equals(endNode)) {
                shortestPaths = path; // Gán danh sách đường đi cần tìm cho đường đi này
                // Thêm vào chuỗi khi đạt đến trạng thái kết thúc
                table.append("\t\t%-7s%-17s%-17s\n".formatted(node, "TTKT/DỪNG", ""));
                break;
            }

            // Trong trường hợp chưa đến được đích, tiếp tục dò đường bằng cách đi tới đỉnh tiếp theo
            List<Node> adjacentNodes = nodeMap.get(node); // Lấy các đỉnh kề với đỉnh cuối cùng đã thăm
            // Kiểm tra nếu các đỉnh kề của KEY - node tồn tại thì thêm một đường đi mới vào trong hàng đợi ban đầu
            if (adjacentNodes != null) {
                for (Node adjacentNode : adjacentNodes) {
                    if (!path.contains(adjacentNode)) { // Kiểm tra nếu như trong đường đi không bị lặp các đỉnh kề
                        List<Node> newPath = new LinkedList<>(path); // Tạo mới một đường đi
                        newPath.add(adjacentNode); // Thêm đỉnh kề vào đường đi
                        queue.add(newPath); // Thêm vào queue để tí kiểm tra xem đỉnh cuối thăm có == trạng thái kết thúc không
                    }
                }

                // Sắp xếp queue theo tiêu chí: trọng số của node cuối cùng trong danh sách các đường đi
                Collections.sort((List<List<Node>>) queue, Comparator.comparingInt(
                        list -> list.getLast().weight
                ));

                // Thêm vào chuỗi để lát nữa in ra bảng liệt kê trạng thái
                table.append("\t\t%-7s%-17s%-17s\n".formatted(node, adjacentNodes,
                                queue.stream().map(List::getLast).collect(Collectors.toList())
                        )
                );

            }
        }
        return shortestPaths;
    }

    private static void addToNodeMap(Node startNode, Node adjNode) {
        nodeMap.computeIfAbsent(startNode, _ -> new ArrayList<>()).add(adjNode);
    }

    /**
     * Lớp Node để chứa đỉnh và trọng số tương ứng
     */
    private static class Node {
        public String top;
        public int weight;

        public Node(String top, int weight) {
            this.top = top;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(top, node.top);
        }

        @Override
        public int hashCode() {
            return Objects.hash(top);
        }

        @Override
        public String toString() {
            return top + weight;
        }
    }
}
