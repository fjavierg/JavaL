<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html"/>
 
  <xsl:template match="/">
    <table border="1">
      <xsl:apply-templates select="csvFile/row"/>
    </table>
  </xsl:template>
 
  <xsl:template match="row">
    <tr>
      <xsl:apply-templates select="column"/>
    </tr>
  </xsl:template>
 
  <xsl:template match="column">
    <td>
      <!-- If a value is empty, print a non-breaking space
           so the HTML table looks OK -->
      <xsl:if test=".=''">
        <xsl:text>&amp;nbsp;</xsl:text>
      </xsl:if>
      <xsl:value-of select="."/>
    </td>
  </xsl:template>
</xsl:stylesheet>