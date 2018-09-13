Browse all the Spider-man comics
================================
This project uses the [Marvel API](http://developer.marvel.com/documentation/getting_started) to fetch all the comics where Spider-man appears.

The project tries to follow clean architecture practices, so it has three main packages: ui, domain and data. It does not always follow it to the letter, for example, sometimes I use the domain model directly in the UI, without mapping it again in the UI layer, I do it for convenience and simplicity. 
There's an [interesting debate](https://softwareengineering.stackexchange.com/questions/303478/uncle-bobs-clean-architecture-an-entity-model-class-for-each-layer) about this. I'm also not using interfaces to communicate between layers, I think in the case of Android is just overengineering things. 
All the layers are in the same module, so having an interface with a single implementation inside the same module it's a little bit too much for my taste.

For the UI layer it follows MVP pattern.

Rx is used in repository methods, so all usecases need to be subcribed to. For convenience, I've put a method to execute usecases in the BasePresenter class that presenters can extend.


Dependency injection is used for almost everything:
  - Each activity has it's own component, module and scope
  - All usecases are injectable. It's enough to add them as a parameter in the presenter constructor, which is really handy and convenient
  - The module that provides the repository is mocked in UI tests using DaggerMock. Using [object mothers](https://martinfowler.com/bliki/ObjectMother.html) it's easy to create UI tests that test domain and ui layers

In ComicListActivity, a scroller is attached to the grid view so that it will trigger a new load request when there's less than 4 items after the current scroll position. 
This activity also has a State class where the curernt state is saved. There's a mapper that takes this state as a parameter and outputs view models for the view.
The recycler view supports 3 types of views, I'm used to using [Renderers](https://github.com/pedrovgs/Renderers) to achieve this functionality, but I was curious to see how it would look using Kotlin and kotlin extensions. The end result is quite simpler than how it was with java and findViewById()

I'm using DiffUtil to update the items in ComicList, although it's not very practical in this particular scenario since we are only adding elements to the list. In any case, I'm used to it and wanted to have it already in place. On the other hand, DiffUtil is causing a warning message when it updates the list while it's being scrolled, I need to look into that.

Set up    <==== VERY IMPORTANT
------

Clone the project and set a real API key in [config.xml](app/src/main/res/common/values/config.xml). You can get one [here](http://developer.marvel.com/documentation/getting_started). 
Without an API key you will only see the error state.

Roadmap
-------
- ~~Add more tests for ComicDetailActivity~~
- ~~Add unit tests for usecases~~
- Save state in an orientation change. Look into [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) to check what they are capable of.
- Introduce a cache dataStore so I can give a try to [Room](https://developer.android.com/topic/libraries/architecture/room), which I haven't tried yet
- Improve the UI, it's very basic now


Screenshots
-----------
![Comic Browser](/../master/screenshots/comic_browser.png?raw=true "Comic broser")
![Comic detail](/../master/screenshots/comic_detail.png?raw=true "Comic detail")
![Broser loading](/../master/screenshots/browser_loading.png?raw=true "Browser loading")
