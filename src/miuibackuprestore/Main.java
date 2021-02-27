package miuibackuprestore;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File srcDir = new File("D:\\桌面\\20210227_111856\\app");
        File outFile = new File("D:\\桌面\\20210227_111856\\descript.xml");
        String head = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><MIUI-backup><jsonMsg></jsonMsg><bakVersion>2</bakVersion><brState>3</brState><autoBackup>false</autoBackup><device>cepheus</device><miuiVersion>21.2.24</miuiVersion><date>1614402143017</date><size>38676864</size><storageLeft>209178025984</storageLeft><supportReconnect>true</supportReconnect><autoRetransferCnt>0</autoRetransferCnt><transRealCompletedSize>0</transRealCompletedSize><packages>";

        StringBuffer stringBuffer = new StringBuffer(head);
        File[] files = srcDir.listFiles();

        for (File file : files) {
            long lenth = FileUtils.getFileLength(file);
            String name = FileUtils.getFileName(file);
            String pachageName = name.substring(name.indexOf("(") + 1, name.indexOf(")"));
            String content = String.format("<package><packageName>%s</packageName><feature>-1</feature><bakFile>%s</bakFile><bakType>2</bakType><pkgSize>%s</pkgSize><sdSize>0</sdSize><state>1</state><completedSize>%s</completedSize><error>0</error><progType>0</progType><bakFileSize>0</bakFileSize><transingCompletedSize>0</transingCompletedSize><transingTotalSize>0</transingTotalSize><transingSdCompletedSize>0</transingSdCompletedSize><sectionSize>0</sectionSize><sendingIndex>0</sendingIndex></package>", pachageName, name, lenth, lenth);
            stringBuffer.append(content);
        }

        String foot = "</packages><filesModifyTime /></MIUI-backup>";
        stringBuffer.append(foot);
        FileUtils.writeFileFromString(outFile, stringBuffer.toString());
        System.out.println("已经全部完成");
    }
}
