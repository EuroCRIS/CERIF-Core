<?xml version="1.0" encoding="UTF-8" ?>
<xslt:stylesheet xmlns:xslt="http://www.w3.org/1999/XSL/Transform"
                 xmlns:xs="http://www.w3.org/2001/XMLSchema"
                 xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                 version="2.0">

    <xslt:param name="baseUri"/>

    <xslt:template match="/*">
        <xslt:copy>
            <xslt:variable name="baseDocument" select="/rdf:*"/>
            <xslt:variable name="baseChildren" select="$baseDocument/element()"/>
            <xslt:for-each select="distinct-values( for $x in /rdf:RDF/element()/@rdf:about[ starts-with( ., $baseUri ) ] return replace( $x, '#.*', '' ) )">
                <xslt:variable name="uri" select="."/>
                <xslt:variable name="className" select="substring-after( $uri, $baseUri )"/>
                <xslt:if test="$uri and matches( $className, '^[A-Z0-9_]+$', 'i' )">
                    <xslt:result-document href="per-class/{$className}.rdf" indent="true">
                        <xslt:for-each select="$baseDocument">
                            <xslt:copy>
                                <xslt:apply-templates select="@*" mode="copy"/>
                                <xslt:for-each select="*[ replace( @rdf:about, '#.*', '' ) = $uri ]">
                                    <xslt:apply-templates select="." mode="copy"/>
                                </xslt:for-each>
                            </xslt:copy>
                        </xslt:for-each>
                    </xslt:result-document>
                </xslt:if>
            </xslt:for-each>
        </xslt:copy>
    </xslt:template>

    <xslt:template match="node()|@*" mode="copy">
        <xslt:copy>
            <xslt:apply-templates select="@*" mode="copy"/>
            <xslt:apply-templates mode="copy"/>
        </xslt:copy>
    </xslt:template>

</xslt:stylesheet>