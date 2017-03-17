package br.com.netsuprema.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.AccessControlException;

public class FileService {
	
	public File[] getArquivosDiretorio(String diretorio) throws IOException{
		File file = new File(diretorio);
		if(file.isDirectory()){		
//			FilenameFilter filter = new FilenameFilter() {
//				@Override
//				public boolean accept(File dir, String name) {
//					return name.toLowerCase().endsWith(".txt");
//				}
//			};
			
			return file.listFiles();
		}else{
			throw new IOException("Diretorio: " +diretorio+ "." +" Não e um diretório valido");
		}
	}
	
	public boolean criarDiretorio(String path) throws AccessControlException{
		File file = new File(path);
		return file.mkdirs();
	}
	
	public boolean diretorioExists(String path){
		return new File(path).exists();
	}
	
	@SuppressWarnings("resource")
	public void copyFile(File origem, File destino) throws IOException {
        if (destino.exists())
            destino.delete();
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(origem).getChannel();
            destinationChannel = new FileOutputStream(destino).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen())
                sourceChannel.close();
            if (destinationChannel != null && destinationChannel.isOpen())
                destinationChannel.close();
       }
   }
}
