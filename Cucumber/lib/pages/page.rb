class  Page  < SitePrism::Page
	set_url 'http://219.244.92.247:8080/BookMark/'

	element :keyword, '#keyword'
	elements :results, '.list'

	elements :ok, '#ok'
	
	elements :deleteItem, "#xxx"

	def search content
		keyword.set content
		sleep 2
	end

	def result
		results.length
	end

	def delete
		deleteItem.click
    		sleep 1
    		ok.click
    		sleep 1
    	end
end