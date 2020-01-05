package honey.mvpexample

class HomePresenterImplp : HomePresenter{
    override fun setDetail(detailData: String) {
        // api here which gives us the data
        // we use view methods to update the data from here

    }

    private lateinit var view : HomeView

    override fun setView(view: HomeView) {
        this.view = view
        view.clickedOnViewMore()
    }

}