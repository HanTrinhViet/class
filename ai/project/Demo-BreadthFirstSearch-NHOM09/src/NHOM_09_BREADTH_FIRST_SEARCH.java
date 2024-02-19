import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.StringTemplate.STR;

/**
 * @author Trịnh Viết Hân
 * @apiNote NHÓM 09
 * @implNote Tìm đường đi ngắn nhất trên đồ thị không trọng số sử dụng thuật toán Breadth First Search
 */
public class NHOM_09_BREADTH_FIRST_SEARCH {
    // Biến static LOGGER dùng để ghi log các lỗi hoặc thông tin
    private static final Logger LOGGER = Logger.getLogger(NHOM_09_BREADTH_FIRST_SEARCH.class.getName());

    // HashMap chứa các đỉnh và đỉnh kề tương ứng xử lý trong queue
    private static final Map<String, List<String>> nodeMap = new HashMap<>();

    // Mutable StringBuilder để sử dụng in ra bảng liệt kê trạng thái
    private static final StringBuilder table
            = new StringBuilder("\t\t%-7s%-17s%-17s\n".formatted("PTTT", "Trạng thái kề", "Danh sách L"));


    /**
     * Phương thức static main để chạy chương trình
     *
     * @param args các tham số đầu vào
     */
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

            // Vòng lặp while đọc vào từng dòng của file 'input.txt' sau 15 dòng đầu tiên
            while (in.hasNextLine()) { // kiểm tra xem có dòng tiếp theo không để còn đọc tiếp ?
                // Đọc vào một dòng, phương thức .nextLine() trả về một String (chuỗi kí tự trong Java, String Pool)
                String line = in.nextLine();

                // Kiểm tra để đọc vào các dòng chứa các đỉnh và jNodesác đỉnh kề tương ứng
                if (!line.contains(">") && line.contains("-")) {
                    String[] data = line.split("-"); // Tách dữ liệu ra qua dấu "-"
                    String node = data[0].trim(); // Lấy ra đỉnh đầu tiên
                    String[] adjNodes = data[1].split(","); // Lấy ra các đỉnh kề với đỉnh đầu tiên rồi nhét vào trong mảng

                    // Thêm đỉnh và các đỉnh kề tương ứng vào trong map (edgeWithAdjacentMap)
                    for (String adjNode : adjNodes) { // adjNode: các đỉnh kề trong adjNodes
                        addToNodeMap(node, adjNode.trim()); // Thêm đỉnh ngọn và các đỉnh kề tương ứng adjNode
                    }
                }

                // Kiểm tra để đọc vào trạng thái bắt đầu và trạng thái kết thúc
                if (line.contains("-") && line.contains(">")) {
                    String[] status = line.split("->"); // tách trạng thái bắt đầu và trạng thái kết thúc qua dấu "->"
                    String startStatus = status[0].trim(); // Lấy ra trạng thái đầu
                    String endStatus = status[1].trim(); // lấy ra trạng thái kết thúc

                    // Tìm đường đi ngắn nhất bằng thuật toán BFS và trả về list theo thứ tự là đường đi
                    List<String> shortestPaths = findShortestPathBreadthFS(startStatus, endStatus);

                    // Tạo đối tượng của PrintWriter để ghi dữ liệu ra file 'output.txt'
                    PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

                    // Hiển thị ra file 'output.txt' trạng thái đầu và trạng thái kết thúc
                    out.println("\t\t====> YỀU CẦU ĐẦU VÀO");
                    out.println(STR."\t\tTrạng thái đầu: \{startStatus}");
                    out.println(STR."\t\tTrạng thái kết thúc: \{endStatus}");

                    // Hiển thị ra file 'output.txt' bảng liệt kê trạng thái
                    out.println("\n\t\t===> BẢNG LIỆT KÊ TRẠNG THÁI");
                    out.println(table);

                    // Hiển thị ra file 'output.txt' đường đi từ trạng thái bắt đầu đến trạng thái kết thúc
                    out.println("\t\t===> KẾT QUẢ");
                    out.print("\t\t");
                    for (String node : shortestPaths) {
                        out.print(STR."\{node}");
                        if (!node.equals(shortestPaths.getLast())) {
                            out.print(" --> ");
                        }
                    }
                    out.close(); // Đóng lại luồng ghi file
                }
            }
            in.close(); // Đóng lại luồng đọc file
        } catch (IOException ex) { // Trong trường hợp file không tồn tại -> ghi log ra lỗi
            LOGGER.warning("File không tồn tại! Vui lòng thử lại!");
        }
    }

    /**
     * Phương thức đường đi từ trạng thái bắt đầu đến trạng thái kết thúc
     *
     * @param start trạng thái ban đầu hoặc đỉnh bắt đầu
     * @param end   trạng thái kết thúc hoặc đỉnh đích
     * @return danh sách đường đi ngắn nhất từ trạng thái ban đầu đến trạng thái kết thúc theo thự tự
     */
    private static List<String> findShortestPathBreadthFS(String start, String end) {
        List<String> shortestPaths = new LinkedList<>(); // danh sách đường đi cần tìm từ đỉnh đầu đến đỉnh đích
        Queue<List<String>> queue = new LinkedList<>();  // hàng đợi để chứa danh sách, mỗi danh sách là một đường đi
        queue.add(Collections.singletonList(start));     // tạo mới một danh sách chứa đỉnh bắt đầu và thêm vào hàng đợi

        // Vòng lặp while duyệt qua danh sách các đường đi trong queue
        while (!queue.isEmpty()) {
            List<String> path = queue.poll(); // poll hay lấy ra phần tử (đường đi) ở đầu hàng đợi
            String node = path.getLast(); // Lấy ra đỉnh cuối cùng đã thăm của đường đi này

            // Kiểm tra nếu như đỉnh cuối cùng đã thăm của đường đi mà trùng với đỉnh dích thì dừng
            if (node.equals(end)) {
                shortestPaths = path; // Gán danh sách đường đi cần tìm cho đường đi này

                // Thêm vào chuỗi khi đạt đến trạng thái kết thúc
                table.append("\t\t%-7s%-17s%-17s\n".formatted(node, "TTKT/DỪNG", ""));
                break;
            }

            // Trong trường hợp chưa đến được đích, tiếp tục dò đường bằng cách đi tới đỉnh tiếp theo
            List<String> adjacentNodes = nodeMap.get(node); // Lấy các đỉnh kề với đỉnh cuối cùng đã thăm

            // Kiểm tra nếu các đỉnh kề của KEY - node tồn tại thì thêm một đường đi mới vào trong hàng đợi ban đầu
            if (adjacentNodes != null) {
                for (String adjacentNode : adjacentNodes) {
                    if (!path.contains(adjacentNode)) { // Kiểm tra nếu như trong đường đi không bị lặp các đỉnh kề
                        List<String> newPath = new LinkedList<>(path); // Tạo mới một đường đi
                        newPath.add(adjacentNode); // Thêm đỉnh kề vào đường đi
                        queue.add(newPath); // Thêm vào queue để tí kiểm tra xem đỉnh cuối thăm có == trạng thái kết thúc không
                    }
                }

                // Thêm vào chuỗi để lát nữa in ra bảng liệt kê trạng thái
                table.append("\t\t%-7s%-17s%-17s\n".formatted(node, adjacentNodes,
                                queue.stream().map(List::getLast).collect(Collectors.toList())
                        )
                );

            }
        }
        return shortestPaths;
    }

    /**
     * Phương thức thêm đỉnh và đỉnh kề tương ứng
     *
     * @param node    đỉnh ngọn
     * @param adjNode đỉnh kề
     * @
     */
    private static void addToNodeMap(String node, String adjNode) {
        // Phương thức default computeIfAbsent(K key, Function<T,V> mappingFunc) của interface Map
        // Phương thức sẽ kiểm tra xem KEY có tồn tại trong map không?
        // --> Nếu không: tạo ra một VALUE với kiểu dữ liệu ArrayList<String> gán với KEY chưa tồn tại đó và thêm vào trong map,
        //                sau đó thêm giá trị đỉnh kề vào ArrayList
        // --> Nếu có: trả về VALUE kiểu ArrayList<String> ứng với KEY và thêm giá trị đỉnh vào ArrayList
        nodeMap.computeIfAbsent(node, _ -> new ArrayList<>()).add(adjNode);
    }
}