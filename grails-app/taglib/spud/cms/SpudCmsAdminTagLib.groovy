package spud.cms
import org.hibernate.FetchMode
class SpudCmsAdminTagLib {
  static defaultEncodeAs = 'html'
    static encodeAsForTags = [pageSelect: 'raw', menuItemSelect: 'raw']
	static namespace = 'spAdmin'

	def grailsApplication
	def spudPageService
	def spudMenuService

	def pageSelect = { attrs, body ->
		def filter = attrs.remove('filter')
		def pageOptions = spudPageService.optionsTreeForPage(filter ? [filter: filter] : [:])
		out << g.select(attrs + [from: pageOptions, optionKey: 'value', optionValue: 'name'])
	}

	def menuItemSelect = { attrs, body ->
		if(!attrs.menu) {
		 throw new IllegalStateException("Property [menu] must be set!")
		}
		def filter = attrs.remove('filter')
		def menu   = attrs.remove('menu')
		def menuOptions = spudMenuService.optionsTreeForItem(menu,filter ? [filter: filter] : [:])

		out << g.select(attrs + [from: menuOptions, optionKey: 'value', optionValue: 'name', noSelection:['':menu.name]])
	}
}

