yieldUnescaped '<!DOCTYPE html>'
html {
	head {
		meta('http-equiv':'"Content-Type" ' + 'content="text/html; charset=utf-8"')
		title('Bundles')
	}
	body {
		style('''
		body{
			font-family: helvetica;
		}
		td {
			background: #e2e9ff;
		}
		tr:nth-child(even) > td{
			background:#f2f5ff;
		}
		th {
			background:#ddd;
			font-weight: bold;
		}''')
		table {
			thead{
				th('ID')
				th('Symbolic name')
				th('Stored')
				th('Active')
			}
			tbody{
				bundles.each { bundle ->
					tr {
						td(bundle.bundleId)
						td(bundle.symbolicName)
						td(bundle.location)
						td(bundle.state == org.osgi.framework.Bundle.ACTIVE)
					}
				}
			}
		}
	}
}
