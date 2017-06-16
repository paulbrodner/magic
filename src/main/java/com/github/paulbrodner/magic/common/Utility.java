package com.github.paulbrodner.magic.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;

import com.github.paulbrodner.magic.exception.TestConfigurationException;

public class Utility
{
    static Logger LOG = LogFactory.getLogger();

    /**
     * Return a file from the filePath location
     * 
     * @param filePath
     * @return file object
     * @throws Exception
     */
    public static File getTestResourceFile(String filePath) throws TestConfigurationException
    {
        // LOG.info("Get resource file {}", filePath);

        URL resource = Utility.class.getClassLoader().getResource(filePath);
        if (resource == null)
        {
            throw new TestConfigurationException(String.format("[%s] file was not found in your main resources folder.", filePath));
        }

        return new File(resource.getFile());
    }

    /**
     * Kill a process using it's name.
     * 
     * @param processName
     * @throws IOException
     */
    public static void killProcessName(String processName) throws IOException
    {
        if (SystemUtils.IS_OS_WINDOWS)
        {
            Runtime.getRuntime().exec(new String[] { "taskkill", "/F", "/IM", processName });
        }
        else
        {
            executeOnUnix("kill `ps ax | grep \"" + processName + "\" | awk '{print $1}'`");
        }
    }

    /**
     * Check if process identified by <processName> is currently running
     * 
     * @param processName
     * @return
     */
    public static boolean isProcessRunning(String processName)
    {
        processName = processName.toLowerCase();
        LOG.info("process name :" + processName);
        Process p = null;
        try
        {
            if (SystemUtils.IS_OS_MAC)
            {
                p = Runtime.getRuntime().exec("ps -ef");
            }
            else if (SystemUtils.IS_OS_WINDOWS)
            {
                p = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "tasklist" });
            }
            InputStream inputStream = p.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferReader.readLine()) != null)
            {
                if (line.toLowerCase().contains(processName))
                    return true;
            }
            inputStream.close();
            inputStreamReader.close();
            bufferReader.close();
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }
        return false;
    }

    /**
     * Execute any Terminal commands
     * Example:
     * executeOnWin("ls -la")
     * 
     * @param command
     * @return
     */
    public static String executeOnUnix(String command)
    {
        LOG.info("On Unix execute command: [{}]", command);

        StringBuilder sb = new StringBuilder();
        String[] commands = new String[] { "/bin/sh", "-c", command };
        try
        {
            Process proc = new ProcessBuilder(commands).start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            String s = null;
            while ((s = stdInput.readLine()) != null)
            {
                sb.append(s);
                sb.append("\n");
            }

            while ((s = stdError.readLine()) != null)
            {
                sb.append(s);
                sb.append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
