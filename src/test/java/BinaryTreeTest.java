import com.binarytree.BinaryTree;
import com.binarytree.Node;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryTreeTest extends TestCase {

    private BinaryTree<String> G0;
    private BinaryTree<String> G1;
    private BinaryTree<String> G2;

    @BeforeEach
    void crearArboles() {
        G0 = null;

        G1 = new BinaryTree<>("1");
        G1.insert("2", G1.getRoot(), true);
        G1.insert("3", G1.getRoot(), false);

        G2 = new BinaryTree<>("1");
        G2.insert("2", G2.getRoot(), true);
        Node<String> node = G2.insert("3", G2.getRoot(), false);
        G2.insert("4", node, true);
        G2.insert("5", node, false);
    }

    @Test
    @DisplayName("Size-1")
    public void test1() {
        Assertions.assertEquals(3, G1.size(), "El bt no calcula bien el tamaño");
    }

    @Test
    @DisplayName("Size-2")
    public void test2() {
        assertThrows(Exception.class, () -> G0.size(), "Debería dar una excepción");
    }

    @Test
    @DisplayName("Depth-1")
    public void test3() {
        Assertions.assertEquals(2, G1.depth(), "No calcula bien la profundidad");
    }

    @Test
    @DisplayName("Depth-2")
    public void test4() {
        assertThrows(Exception.class, () -> G0.depth(), "Debería dar una excepción");
    }

    @Test
    @DisplayName("DepthNode-1")
    public void test5() {
        Assertions.assertEquals(2, G2.depth(G2.search("3")), "No calcula bien la profundidad de un nodo");
    }

    @Test
    @DisplayName("DepthNode-2")
    public void test6() {
        Assertions.assertThrows(Exception.class, () -> G2.depth(G2.search("6")), "El nodo no debería tener profundidad");
    }

    @Test
    @DisplayName("DepthNode-3")
    public void test7() {
        Assertions.assertThrows(Exception.class, () -> G0.depth(G0.search(("3"))), "El grafo no debería existir");
    }

    @Test
    @DisplayName("DepthNode-4")
    public void test8() {
        Assertions.assertThrows(Exception.class, () -> G0.depth(G0.search(("6"))), "El grafo no debería existir");
    }

    @Test
    @DisplayName("GetRoot-1")
    public void test9() {
        Node<String> expected = new Node<>("1");
        expected.setLeftChild(new Node<>("2"));
        expected.setRightChild(new Node<>("3"));
        Assertions.assertEquals(expected.toString(), G1.getRoot().toString(), "getRoot no devuelve la raiz");
    }

    @Test
    @DisplayName("GetRoot-2")
    public void test10() {
        Assertions.assertThrows(Exception.class, () -> G0.getRoot(), "Debería dar una excepción");
    }


    @Test
    @DisplayName("Insert-1")
    public void test11() {
        Node<String> nodo = G2.insert("6", G2.getRoot().getLeftChild(), true);

        Node<String> expected = new Node<>("6");
        expected.setParent(new Node<>("2"));
        Assertions.assertEquals(expected.toString(), nodo.toString(), "Los nodos deberían ser iguales");
    }

    @Test
    @DisplayName("Insert-2")
    public void test12() {
        Node<String> nodo = G2.insert("6", G2.getRoot().getLeftChild(), false);

        Node<String> expected = new Node<>("6");
        expected.setParent(new Node<>("2"));
        Assertions.assertEquals(expected.toString(), nodo.toString(), "Los nodos deberían ser iguales");
    }

    @Test
    @DisplayName("Insert-4 e Insert-5")
    public void test13() {
        Assertions.assertThrows(
                Exception.class,
                () -> G2.insert("6", G2.search("7"), false),
                "Debería saltar una excepción"
        );
    }

    @Test
    @DisplayName("Insert-7 Insert-8 Insert-10 Insert-11")
    public void test14() {
        Assertions.assertThrows(
                Exception.class,
                () -> G2.insert("+", G2.search("2"), false),
                "Debería saltar una excepción"
        );
    }

    @Test
    @DisplayName("Insert-13 y superior")
    public void test15() {
        Assertions.assertThrows(
                Exception.class,
                () -> G0.insert("6", G2.search("2"), true),
                "Debería saltar una excepción"
        );
    }

    @Test
    @DisplayName("Remove-1")
    public void test16() {
        G1.remove(G1.search("2"));
        Assertions.assertNull(
                G1.getRoot().getLeftChild(),
                "No debería de tener el hijo"
        );
    }

    @Test
    @DisplayName("Remove-2")
    public void test17() {
        Assertions.assertThrows(
                Exception.class,
                () -> G1.remove(G1.search("5")),
                "Debería dar una excepción"
        );
    }

    @Test
    @DisplayName("Remove-3 Remove-4")
    public void test18() {
        Assertions.assertThrows(
                Exception.class,
                () -> G0.remove(G0.search("2")),
                "Debería dar una excepción"
        );
    }

    @Test
    @DisplayName("Search-1")
    public void test19() {
        String content = "2";
        Node<String> node = G1.search(content);
        Assertions.assertEquals(node.getContent(), content, "El contenido del nodo debería ser igual");
        Assertions.assertNull(node.getLeftChild(), "El nodo no debería tener un hijo izquierdo");
        Assertions.assertNull(node.getRightChild(), "El nodo no debería tener un hijo derecho");
    }

    @Test
    @DisplayName("Search-2")
    public void test20() {
        String content = "5";
        Assertions.assertNull(G1.search(content), "El nodo no debería estar en el árbol");
    }

    @Test
    @DisplayName("Search-3")
    public void test21() {
        Assertions.assertThrows(
                Exception.class,
                () -> G1.search("+"),
                "Debería dar una excepción"
        );
    }


    @Test
    @DisplayName("Equals-1")
    public void test22() {
        Assertions.assertTrue(G1.equals(G1), "El grafo debería ser el mismo");
    }

    @Test
    @DisplayName("Equals-2")
    public void test23() {
        Assertions.assertFalse(G1.equals(G2), "El grafo debería ser diferente");
    }

    @Test
    @DisplayName("ToList-1")
    public void test24() {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList("1", "2", "3"));
        Assertions.assertEquals(arr, G1.toList(), "Las listas deberían ser iguales");
    }

    @Test
    @DisplayName("GetSubTree-1")
    public void test25() {
        Node<String> res = G2.search("3");
        BinaryTree<String> bt = G2.getSubTree(res);
        Assertions.assertEquals("3", bt.getRoot().getContent(), "El contenido debería ser igual");
        Assertions.assertEquals("4", bt.getRoot().getLeftChild().getContent(), "El hijo izquierdo no coincide");
        Assertions.assertEquals("5", bt.getRoot().getRightChild().getContent(), "El hijo derecho no coincide");
    }

    @Test
    @DisplayName("GetSubTree-2")
    public void test26() {
        Assertions.assertThrows(
                Exception.class,
                () -> G2.getSubTree(new Node<>("6")),
                "Debería dar un error"
        );
    }

    @Test
    @DisplayName("Iterator-1")
    public void test27() {
        Iterator<String> it = G1.iterator();
        List<String> list = new ArrayList<>();
        while(it.hasNext()) {
            list.add(it.next());
        }
        Assertions.assertEquals(G1.toList(), list, "El iterador no es correcto");
    }

    @Test
    @DisplayName("ToString-1")
    public void test30() {
        Assertions.assertEquals("1 Left: 2 Right: 3 \n2 \n3".trim(), G1.toString().trim());
    }
}
