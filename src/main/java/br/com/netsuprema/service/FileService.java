package br.com.netsuprema.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.AccessControlException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.netsuprema.service.cedente.DiretoriosEnvioService;

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
	
	public boolean criarArquivo(String path, String conteudo) throws IOException{
		File file = new File(path);
		return file.createNewFile();
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

	public File abrirArquivoLog() throws IOException {
		String nomeArquivo = DiretoriosEnvioService.DIRETORIO_PADRAO + "log.txt";
		FileService fileService = new FileService();
		if(fileService.diretorioExists(nomeArquivo)){
			return new File(nomeArquivo);
		}else{
			 File file = new File(nomeArquivo);
			 file.createNewFile();
			 return file;
		}
	}

	public String obterConteudoDoArquivo(File file) throws Exception {
		try {
			StringBuilder conteudo 	= new StringBuilder();
			FileReader fileReader 	= new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			
			while (bufferReader.ready()) {
				conteudo.append(bufferReader.readLine());
			}
			
			bufferReader.close();
			fileReader.close();
			
			return conteudo.toString();
			
		} catch (IOException e) {
			throw new Exception("Erro ao tentar obter o conteudo do arquivo. Motivo: " + e.getMessage());
		}
	}

	public void salvarLog(List<String> logErros) throws IOException {
		
		Date data = new Date(System.currentTimeMillis());  
		SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd"); 
		String format = formatarDate.format(data);
		
		File file = new File(DiretoriosEnvioService.DIRETORIO_PADRAO + "log-"+format+".txt");
		FileWriter fileW = new FileWriter (file, true);
        BufferedWriter buffW = new BufferedWriter (fileW);
        buffW.newLine();
        
        logErros.stream().forEach(x-> {
			try {
				buffW.newLine();
				buffW.write(x);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
    
        buffW.close();
        fileW.close();
		
	}
}
