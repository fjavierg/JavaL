<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" />

	<xsl:template match="/">
		<transformed>
			<xsl:apply-templates select="csvFile/row" />
		</transformed>
	</xsl:template>

	<xsl:template match="row">
		INSERT INTO <row>
			<newColumn1>
				<xsl:value-of select="column[1]" />
			</newColumn1>
			<newColumn2>
				<xsl:value-of select="concat(column[1],'---',column[2])" />
			</newColumn2>
			<newColumn3>
				<xsl:choose>
					<xsl:when test="contains(column[1],'Jef')">
						<xsl:text>YESSSS it contains Jef;</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>No it does not contain Jef;</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</newColumn3>

		</row>
	</xsl:template>


</xsl:stylesheet>