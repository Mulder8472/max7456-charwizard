package net.codehustler.max7456.charwizard.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;


public class UserSettings
{
  private static final int MAX_FILE_HISTORY = 10;
  private List<String> recentFiles = new ArrayList();

  private Properties properties = new Properties();


  public UserSettings load()
  {
    try
    {
      this.properties.load(new FileInputStream(getUserSettingsFile()));


      for (Iterator localIterator = this.properties.keySet().iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();

        String key = (String)o;

        if (!(key.startsWith("RECENT_")))
          continue;
        addRecentFile(this.properties.getProperty(key));
      }
    }
    catch (IOException e)
    {
      System.err.println("Unable to load user settings!");

    }

    return null;
  }

  public void save()
  {
    try
    {
      int index = 0;
      for (String recentFile : getRecentFiles())
      {
        this.properties.setProperty("RECENT_" + index, recentFile);
        ++index;
      }

      this.properties.store(new FileOutputStream(getUserSettingsFile()), "MAX7456 Charwizard User Settings");
    } catch (IOException e) {
      System.err.println("Unable to save user settings!");
    }
  }

  public static File getUserSettingsFile()
    throws IOException
  {
    File f = new File(getUserDir() + File.separator + ".settings");
    if (!(f.exists())) f.createNewFile();

    return f;
  }


  public static File getUserDir()
  {
    File f = new File(System.getProperty("user.home") + File.separatorChar + ".max7456MCMWizard");
    if (!(f.exists())) f.mkdir();

    return f;
  }




  public void setLastUsedDirectory(String lastUsedDirectory)
  {
    this.properties.setProperty("lastUsedDirectory", lastUsedDirectory);
  }



  public String getLastUsedDirectory()
  {
    return this.properties.getProperty("lastUsedDirectory");
  }


  public void addRecentFile(String file)
  {
    if (this.recentFiles.contains(file)) {
      this.recentFiles.remove(file);
    }
    this.recentFiles.add(file);


    if (this.recentFiles.size() <= 10)
      return;
    this.recentFiles.remove(this.recentFiles.size() - 1);
  }



  public List<String> getRecentFiles()
  {
    return this.recentFiles;
  }
}
