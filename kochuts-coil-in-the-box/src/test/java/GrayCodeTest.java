import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrayCodeTest {
    Stack<Integer> expected;

    @BeforeEach
    public void init() {
        expected = new Stack<>();
    }

    @AfterEach
    public void cleanUp() {
        expected = null;
    }

    @Test
    public void checkGrayCodeForDim0() {
        expected.push(0);
        assertEquals(GrayCode.createGrayCode(0), expected);
    }

    @Test
    public void checkGrayCodeForDim1() {
        expected.push(0);
        expected.push(1);
        assertEquals(GrayCode.createGrayCode(1), expected);
    }

    @Test
    public void checkGrayCodeForDim2() {
        expected.push(0);
        expected.push(1);
        expected.push(3);
        expected.push(2);
        assertEquals(GrayCode.createGrayCode(2), expected);
    }

    @Test
    public void checkGrayCodeForDim3() {
        expected.push(0);
        expected.push(1);
        expected.push(3);
        expected.push(2);
        expected.push(6);
        expected.push(7);
        expected.push(5);
        expected.push(4);
        assertEquals(GrayCode.createGrayCode(3), expected);
    }

    @Test
    public void checkGrayCodeForDim7() {
        expected.push(0);
        expected.push(1);
        expected.push(3);
        expected.push(2);
        expected.push(6);
        expected.push(7);
        expected.push(5);
        expected.push(4);
        expected.push(12);
        expected.push(13);
        expected.push(15);
        expected.push(14);
        expected.push(10);
        expected.push(11);
        expected.push(9);
        expected.push(8);
        expected.push(24);
        expected.push(25);
        expected.push(27);
        expected.push(26);
        expected.push(30);
        expected.push(31);
        expected.push(29);
        expected.push(28);
        expected.push(20);
        expected.push(21);
        expected.push(23);
        expected.push(22);
        expected.push(18);
        expected.push(19);
        expected.push(17);
        expected.push(16);
        expected.push(48);
        expected.push(49);
        expected.push(51);
        expected.push(50);
        expected.push(54);
        expected.push(55);
        expected.push(53);
        expected.push(52);
        expected.push(60);
        expected.push(61);
        expected.push(63);
        expected.push(62);
        expected.push(58);
        expected.push(59);
        expected.push(57);
        expected.push(56);
        expected.push(40);
        expected.push(41);
        expected.push(43);
        expected.push(42);
        expected.push(46);
        expected.push(47);
        expected.push(45);
        expected.push(44);
        expected.push(36);
        expected.push(37);
        expected.push(39);
        expected.push(38);
        expected.push(34);
        expected.push(35);
        expected.push(33);
        expected.push(32);
        expected.push(96);
        expected.push(97);
        expected.push(99);
        expected.push(98);
        expected.push(102);
        expected.push(103);
        expected.push(101);
        expected.push(100);
        expected.push(108);
        expected.push(109);
        expected.push(111);
        expected.push(110);
        expected.push(106);
        expected.push(107);
        expected.push(105);
        expected.push(104);
        expected.push(120);
        expected.push(121);
        expected.push(123);
        expected.push(122);
        expected.push(126);
        expected.push(127);
        expected.push(125);
        expected.push(124);
        expected.push(116);
        expected.push(117);
        expected.push(119);
        expected.push(118);
        expected.push(114);
        expected.push(115);
        expected.push(113);
        expected.push(112);
        expected.push(80);
        expected.push(81);
        expected.push(83);
        expected.push(82);
        expected.push(86);
        expected.push(87);
        expected.push(85);
        expected.push(84);
        expected.push(92);
        expected.push(93);
        expected.push(95);
        expected.push(94);
        expected.push(90);
        expected.push(91);
        expected.push(89);
        expected.push(88);
        expected.push(72);
        expected.push(73);
        expected.push(75);
        expected.push(74);
        expected.push(78);
        expected.push(79);
        expected.push(77);
        expected.push(76);
        expected.push(68);
        expected.push(69);
        expected.push(71);
        expected.push(70);
        expected.push(66);
        expected.push(67);
        expected.push(65);
        expected.push(64);
        assertEquals(GrayCode.createGrayCode(7), expected);
    }
}
