package me.haosdent.cgroup.subsystem;

import me.haosdent.cgroup.manage.Group;
import me.haosdent.cgroup.util.Constants;

import java.io.IOException;

public class Memory extends Common {

  public static final int SUBSYS = Constants.SUBSYS_MEMORY;
  public static final String PROP_MEMORY_STAT = "memory.stat";
  public static final String PROP_MEMORY_USAGE_IN_BYTES = "memory.usage_in_bytes";
  public static final String PROP_MEMORY_MEMSW_USAGE_IN_BYTES = "memory.memsw.usage_in_bytes";
  public static final String PROP_MEMORY_MAX_USAGE_IN_BYTES = "memory.max_in_bytes";
  public static final String PROP_MEMORY_MEMSW_MAX_USAGE_IN_BYTES = "memory.memsw.usage_in_bytes";
  public static final String PROP_MEMORY_LIMIT_IN_BYTES = "memory.limit_in_bytes";
  public static final String PROP_MEMORY_MEMSW_LIMIT_IN_BYTES = "memory.memsw.limit_in_bytes";
  public static final String PROP_MEMORY_FAILCNT = "memory.failcnt";
  public static final String PROP_MEMORY_MEMSW_FAILCNT = "memory.memsw.failcnt";
  public static final String PROP_MEMORY_FORCE_EMPTY = "memory.force_empty";
  public static final String PROP_MEMORY_SWAPPINESS = "memory.swappiness";
  public static final String PROP_MEMORY_USE_HIERARCHY = "memory.use_hierarchy";
  public static final String PROP_MEMORY_OOM_CONTROL = "memory.oom_control";

  public Memory(Group group) {
    super(group);
  }

  public static class Stat {
    public final long cacheSize;
    public final long rssSize;
    public final long mappedFileSize;
    public final long pgpginNum;
    public final long pgpgoutNum;
    public final long swapSize;
    public final long activeAnonSize;
    public final long inactiveAnonSize;
    public final long activeFileSize;
    public final long inactiveFileSize;
    public final long unevictableSize;
    public final long hierarchicalMemoryLimitSize;
    public final long hierarchicalMemswLimitSize;
    public final long totalCacheSize;
    public final long totalRssSize;
    public final long totalMappedFileSize;
    public final long totalPgpginNum;
    public final long totalPgpgoutNum;
    public final long totalSwapSize;
    public final long totalActiveAnonSize;
    public final long totalInactiveAnonSize;
    public final long totalActiveFileSize;
    public final long totalInactiveFileSize;
    public final long totalUnevictableSize;
    public final long totalHierarchicalMemoryLimitSize;
    public final long totalHierarchicalMemswLimitSize;

    public Stat(String output) {
      String[] splits = output.split("\n");
      this.cacheSize = Long.parseLong(splits[0]);
      this.rssSize = Long.parseLong(splits[1]);
      this.mappedFileSize = Long.parseLong(splits[2]);
      this.pgpginNum = Long.parseLong(splits[3]);
      this.pgpgoutNum = Long.parseLong(splits[4]);
      this.swapSize = Long.parseLong(splits[5]);
      this.inactiveAnonSize = Long.parseLong(splits[6]);
      this.activeAnonSize = Long.parseLong(splits[7]);
      this.inactiveFileSize = Long.parseLong(splits[8]);
      this.activeFileSize = Long.parseLong(splits[9]);
      this.unevictableSize = Long.parseLong(splits[10]);
      this.hierarchicalMemoryLimitSize = Long.parseLong(splits[11]);
      this.hierarchicalMemswLimitSize = Long.parseLong(splits[12]);
      this.totalCacheSize = Long.parseLong(splits[13]);
      this.totalRssSize = Long.parseLong(splits[14]);
      this.totalMappedFileSize = Long.parseLong(splits[15]);
      this.totalPgpginNum = Long.parseLong(splits[16]);
      this.totalPgpgoutNum = Long.parseLong(splits[17]);
      this.totalSwapSize = Long.parseLong(splits[18]);
      this.totalInactiveAnonSize = Long.parseLong(splits[19]);
      this.totalActiveAnonSize = Long.parseLong(splits[20]);
      this.totalInactiveFileSize = Long.parseLong(splits[21]);
      this.totalActiveFileSize = Long.parseLong(splits[22]);
      this.totalUnevictableSize = Long.parseLong(splits[23]);
      this.totalHierarchicalMemoryLimitSize = Long.parseLong(splits[24]);
      this.totalHierarchicalMemswLimitSize = Long.parseLong(splits[25]);
    }
  }

  public Stat getStat() throws IOException {
    String output = shell.cgget(group.getName(), PROP_MEMORY_STAT);
    Stat stat = new Stat(output);
    return stat;
  }

  public long getPhysicalUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public long getWithSwapUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MEMSW_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public long getMaxPhysicalUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MAX_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public long getMaxWithSwapUsage() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MEMSW_USAGE_IN_BYTES);
    return Long.parseLong(result);
  }

  public void setPhysicalUsageLimit(long value) throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_LIMIT_IN_BYTES, value + "");
  }

  public long getPhysicalUsageLimit() throws IOException {
    String output = shell.cgget(group.getName(), PROP_MEMORY_LIMIT_IN_BYTES);
    return Long.parseLong(output);
  }

  public void setWithSwapUsageLimit(long value) throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_MEMSW_LIMIT_IN_BYTES, value + "");
  }

  public long getWithSwapUsageLimit() throws IOException {
    String output = shell.cgget(group.getName(), PROP_MEMORY_MEMSW_LIMIT_IN_BYTES);
    return Long.parseLong(output);
  }

  public int getPhysicalFailCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_FAILCNT);
    return Integer.parseInt(result);
  }

  public int getWithSwapFailCount() throws IOException {
    String result = shell.cgget(group.getName(), PROP_MEMORY_MEMSW_FAILCNT);
    return Integer.parseInt(result);
  }

  public void clearForceEmpty() throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_FORCE_EMPTY, 0 + "");
  }

  public void setSwappiness(int value) throws IOException {
    shell.cgset(group.getName(), PROP_MEMORY_SWAPPINESS, value + "");
  }

  public int getSwappiness() throws IOException {
    return Integer.parseInt(shell.cgget(group.getName(), PROP_MEMORY_SWAPPINESS));
  }

  public void setUseHierarchy(boolean flag) throws IOException {
    int value = flag? 1 : 0;
    shell.cgset(group.getName(), PROP_MEMORY_USE_HIERARCHY, value + "");
  }

  public boolean isUseHierarchy() throws IOException {
    int output = Integer.getInteger(shell.cgget(group.getName(), PROP_MEMORY_USE_HIERARCHY));
    return output > 0;
  }

  public void setOomControl(boolean flag) throws IOException {
    int value = flag? 1 : 0;
    shell.cgset(group.getName(), PROP_MEMORY_OOM_CONTROL, value + "");
  }

  public boolean isOomControl() throws IOException {
    int output = Integer.parseInt(shell.cgget(group.getName(), PROP_MEMORY_OOM_CONTROL));
    return output > 0;
  }
}
