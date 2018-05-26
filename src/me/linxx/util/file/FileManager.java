package me.linxx.util.file;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;


public class FileManager {

	String filename;
	private File file;
	private String path;
	private YamlConfiguration cfg;

	public FileManager( String filename ) {
		this.filename = filename;
		cfg = new YamlConfiguration();

		this.file = new File( "plugins" + File.separator + "BuyHeart", this.filename );
		if ( !( this.file.exists() ) ) {
			File file2 = new File( "plugins" + File.separator + "BuyHeart" );
			file2.mkdirs();
			extractcfg();

		}
		try {
			this.cfg.load( this.file );
		} catch ( IOException | InvalidConfigurationException e ) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			this.cfg.save( this.file );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			this.cfg.load( this.file );
		} catch ( IOException | InvalidConfigurationException e ) {
			e.printStackTrace();
		}
	}

	public YamlConfiguration getConfig() {
		return cfg;
	}

	private void extractcfg() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream( filename );
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream( file );
			int read = 0;
			byte[] bytes = new byte[1024];
			while ( ( read = inputStream.read( bytes ) ) != -1 ) {
				outputStream.write( bytes, 0, read );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if ( inputStream != null ) {
				try {
					inputStream.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
			if ( outputStream != null ) {
				try {
					outputStream.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}

			}
		}
	}

}