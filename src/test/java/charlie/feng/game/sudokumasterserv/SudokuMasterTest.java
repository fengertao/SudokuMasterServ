/*
 * Copyright (c) 2018-2019,  Charlie Feng. All Rights Reserved.
 */

package charlie.feng.game.sudokumasterserv;

import charlie.feng.game.sudokumasterserv.master.Grid;
import charlie.feng.game.sudokumasterserv.master.SudokuMaster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SudokuMasterTest {

    @DisplayName("Grid Play result should as expected")
    @ParameterizedTest(name = "{index} => gridId={0}, expectedSolution={1}, expectedResolved={2}")
    @CsvSource({
            "000000018948007050000008020053702000009000000000901430090600000030500876060000000, 325496718948127653176358924653742189419835267782961435891673542234519876567284391, 81",
            "460001000002096000030000068000000037000607000510000000840000050000710900000300024, 465831279782496315139572468694125837328647591517983642841269753253714986976358124, 81",
            "030000740000000080000485620200009000060104030000800009096731000020000000017000060, 538296741642317985971485623283579416769124538154863279496731852325648197817952364, 81",
            "002100000060000750010008004009010008000304000100090500600200070058000040000009300, 472165893863942751915738624239517468586324917147896532694283175358671249721459386, 81",
            "010000052204000397070302014007060200000509100006020900080407520705000401040000730, 319674852264185397578392614197863245832549176456721983683417529725938461941256738, 81",
            "000750000030048020100000006040000008790000031200000070500000007080320040000069000, 862751493937648125154293786346172958795486231218935674529814367681327549473569812, 81",
            "000721000580000002000000000010958004000000000040102007920865030400217005650493020, 364721589589346172172589346713958264295674813846132957927865431438217695651493728, 81",
            "472000000105760004000000000000080000047200000830401002580020070000308029004070010, 472835691195762834368914257251687943647293185839451762583129476716348529924576318, 81",
            "062070000000000000040691070801029600090007800000063500300000000027000060010050040, 162375489739284156548691273851429637693517824274863591385946712427138965916752348, 81",
            "000000456000000000050046092280700003600010500000008000000000300800904600020800001, 198237456462589137357146892284765913639412578571398264745621389813974625926853741, 81",
            "000057080300000000002900400200809300400000007007406008006002700000000005090310000, 649257183378641529512983476261879354483125697957436218836592741124768935795314862, 81",
            "002000030900100006040007500800200100000010000005008007008300040600001003090000200, 562489731987153426341627598879236154436715982215948367158372649624591873793864215, 81", //X-Wing
            "098130002400952001152860300501006000080091030000400106817020060000718003320640718, 698137542473952681152864397541376829786291435239485176817523964964718253325649718, 81", //XY-Wing
            "400085012150206384082413705201068003005324801800107006596832147314009008728641539, 439785612157296384682413795271968453965324871843157926596832147314579268728641539, 81", //XY-Wing
            "100030000800102000090040150000004618004001293901300745035010070009203001018060009, 152937864846152937397648152523794618674581293981326745235819476469273581718465329, 81", //XY-Wing
            "036100740470630500502470603600380274007024106240761905709043861060807429004006357, 936152748478639512512478693691385274857924136243761985729543861365817429184296357, 81", //Sword Fish
            "060000040007006003800030100002407000700000005000305200006020001900100300040000050, 263951748417286593859734126532467819794812635681395274376529481925148367148673952, 81", //Sword Fish
            "100000002030040050002506700008901500000070000007405200001607900060090010900000008, 154783692736249851892516734648921573529378146317465289481637925265894317973152468, 81", //Jelly Fish
            "910070080004900020300000000000000002000000100000800300000000068002500004000420900, 915274683684913527327685419456139872238746195179852346541397268892561734763428951, 25",
            "001002300043000002050100000000006020400257003070800000000003080800000750005600100, 981462375643785912257139648598316427416257893372894561169573284834921756725648139, 65",
            "123000040005060070000008000000006003600437002400100000000800000040020600010000327, 123795846985364271764218539872956413651437982439182765296873154347521698518649327, 58",
            "001200000034000050000050306700000020005684700040000003502070000070000210000001400, 651243987934867152287159346796315824325684791148792563512478639479536218863921475, 57",
            "002010000040500360706300500000100000500030007000004000004001203061003040000020800, 352618974149572368786349521497185632518236497623794185874961253261853749935427816, 67",
            "000300504007000060015002000009028006000010000500490700000500680060000300802006000, 928367514347159268615842973139728456274615839586493721493571682761284395852936147, 66",
            "000000005090400002675100000003009400900040007001600200000003594200005060100000000, 412937685398456172675182943783529416926341857541678239867213594234895761159764328, 60",
            "010000300000509082000600190400300050000040000030008007076004000540107000008000030, 915482376763519482824673195487321659259746813631958247376894521542137968198265734, 57",
            "080006010500803000090040500402000000900010008000000905005060040000204001060900030, 287596314541823796396147582452789163973615428618432975725361849839274651164958237, 55",
            "500300182018250397002080456080000039120030800003800000294503608007098520851062903, 569347182418256397372981456785614239126739845943825761294573618637198524851462973, 45",
            "030009050010000804700002000000006090005837400080900000000100009103000040020700010, 432689157916375824758412936371246598295837461684951273547123689163598742829764315, 69",
            "009400007000070000800002096001000529000040000657000300120800004000090000500006800, 269483157415679283873512496341768529982345761657921348126857934738294615594136872, 65",
            "010000079600000003009065000800700090000854000050003008000610900700000002180000030, 514382679628971453379465821831726594962854317457193268243617985796538142185249736, 63",
            "000001003004800500016040000280400000500080006000002097000070980008009100600500000, 875921643324867519916345278287496351591783426463152897132674985758239164649518732, 62",
            "004002100000500030030040090400200080300090007070008009080030010090001000002900800, 954382176726519438831746295469273581318495627275168349587634912693821754142957863, 59",
            "000008004409000800006300590800050000040090060000060007095007200004000306600400000, 512978634439526871786341592861754923247893165953162487395617248174289356628435719, 58"
    })
    public void testPlay(String gridId, String expectedSolution, int expectedResolved) {
        Grid grid = new Grid(gridId);
        new SudokuMaster().play(grid);
        Assertions.assertTrue(grid.isResolved(), "Grid " + gridId + " should be resolved");
        Assertions.assertEquals(expectedSolution, grid.getAnswer(), "Grid " + gridId + " solution should be correct");

        grid = new Grid(gridId);
        grid.setExpectedAnswer(new Grid(expectedSolution));
        new SudokuMaster().playGracefully(grid);
        if (expectedResolved == 81) {
            Assertions.assertTrue(grid.isResolved(), "Grid " + gridId + " should be resolved");
            Assertions.assertEquals(expectedSolution, grid.getAnswer(), "Grid " + gridId + " solution should be correct");
        } else {
            Assertions.assertFalse(grid.isResolved(), "Grid " + gridId + " should be not resolved yet. get result:" + grid.getAnswer());
            Assertions.assertEquals(expectedResolved, grid.getNumberOfResolvedCells(), "Resolved cell number should as expected");
        }
    }

}
