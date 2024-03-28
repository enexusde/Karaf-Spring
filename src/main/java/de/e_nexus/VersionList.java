package de.e_nexus;

/**
 * Wrapper for version-list.
 *
 * <p>
 * Will create this output in example:
 * </p>
 *
 * <code>
 * version:List&lt;Version&gt;="1.0,1.1", osgi.ee
 * </code>
 *
 * @param ns       The namespace, never <code>null</code>
 * @param type     The type, never <code>null</code>
 * @param scope    The scope, never <code>null</code>
 * @param versions The versions, never <code>null</code>
 */
public record VersionList(String ns, String type, String scope,
        Iterable<?> versions) {
    /**
     * Returns this version as a string.
     *
     * @return This version as a string
     */
    public String asString() {
        StringBuilder result = new StringBuilder();
        result.append(ns);
        if (type != null) {
            result.append(":");
            result.append(type);
        }
        result.append("=\"");
        boolean first = true;
        for (var ver : versions) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(ver);
        }
        result.append("\"");
        if (scope != null) {
            result.append(", ");
            result.append(scope);
        }
        return result.toString();
    }
}
