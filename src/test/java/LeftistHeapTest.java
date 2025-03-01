import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeftistHeapTest {

    @Test // Черный ящик
    void testBasicFunctionality() {
        LeftistHeap heap = new LeftistHeap();
        assertTrue(heap.isEmpty());
        heap.insert(10);
        heap.insert(5);
        assertFalse(heap.isEmpty());
        assertEquals(5, heap.deleteMin());
        assertEquals(10, heap.deleteMin());
        assertTrue(heap.isEmpty());
    }

    @Test // Серый ящик
    void testMergeAfterDeletions() {
        LeftistHeap heap1 = new LeftistHeap();
        heap1.insert(8);
        heap1.insert(4);
        LeftistHeap heap2 = new LeftistHeap();
        heap2.insert(7);
        heap2.insert(2);
        heap1.merge(heap2);
        assertEquals(2, heap1.deleteMin());
        assertEquals(4, heap1.deleteMin());
        assertEquals(7, heap1.deleteMin());
        assertEquals(8, heap1.deleteMin());
    }

    @Test // Белый ящик
    void testLeftistPropertyInternal() {
        LeftistHeap heap = new LeftistHeap();
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(15);
        LeftistHeap.Node root = heap.getRoot();
        assertTrue(root.getLeft().getRank() >= root.getRight().getRank());
        assertEquals(2, root.getRank());
    }

    @Test
    void testLeftistPropertySwap() {
        LeftistHeap heap = new LeftistHeap();
        heap.insert(1);
        heap.insert(3);
        heap.insert(2);
        heap.insert(5);
        heap.insert(4);

        assertEquals(1, heap.deleteMin());

        LeftistHeap.Node root = heap.getRoot();
        assertNotNull(root.getLeft());
        assertNotNull(root.getRight());

        assertTrue(root.getLeft().getRank() >= root.getRight().getRank());
    }

    @Test
    void testDeleteMinOnEmptyHeapThrowsException() {
        LeftistHeap heap = new LeftistHeap();
        assertThrows(IllegalStateException.class, heap::deleteMin);
    }
}
