package br.com.netsuprema.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {
	
	public List<File> obterArquivosParaEnvioDiretorio(String diretorio) throws IOException{
		List<File> files = filtrarArquivosPorMineType(Arrays.asList(getArquivosDiretorio(diretorio)));
		return files;
	}
	
	public File[] getArquivosDiretorio(String diretorio) throws IOException{
		File file = new File(diretorio);
		if(file.isDirectory()){		
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
        
       destino.createNewFile();
        
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

	public List<File> filtrarArquivosPorMineType(List<File> files) {
		List<File> filesFiltrados = new ArrayList<File>();
		for (File arq : files) {
			String mimiType = URLConnection.guessContentTypeFromName(arq.getName());
			if ((mimiType!= null) && (mimiType.equals("text/plain"))) {
				filesFiltrados.add(arq);
			}
		}
		return filesFiltrados;
	}
	
	public static void main(String[] args) throws IOException {
		FileService service = new FileService();
		File origem = new File("C:/Van Remessas/dir/arq/452707/26168/a.java");
		service.copyFile(origem, new File("C:/Van Remessas/dir/arq/452707/26168/enviados/a.txt"));
		origem.delete();
	}
}
