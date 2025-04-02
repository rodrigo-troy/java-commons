package com.rodrigotroy.commons.core.util.print;

import java.util.List;
import java.util.Map;

public interface IMapUtil {

    String mapValuesToString(Map<String, Object> mapa) throws
                                                       RuntimeException;

    List<List<Object>> mapEntriesToList(Map<String, Object> mapa) throws
                                                                  RuntimeException;
}
