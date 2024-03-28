package de.e_nexus;

/**
 * Wrapper for version-list.
 *
 * @param ns       The namespace, never <code>null</code>
 * @param type     The type, never <code>null</code>
 * @param scope    The scope, never <code>null</code>
 * @param versions The versions, never <code>null</code>
 */
public record VersionList(String ns, String type, String scope,
        Iterable<String> versions) {
    /**
     * Returns this version as a string.
     *
     * @return This version as a string
     */
    public String asString() {
        StringBuilder result = new StringBuilder();
        for (String string : versions) {
            result.append(",");
            result.append(string);
        }
        if (result.length() > 1) {
            result = result.deleteCharAt(0);
        }
        result.append(ns);
        if (type != null) {
            result.append(":");
            result.append(type);
        }
        result.append("=\"");
        result.append(result);
        result.append("\"");
        if (scope != null) {
            result.append(", ");
            result.append(scope);
        }
        return result.toString();
    }
}
