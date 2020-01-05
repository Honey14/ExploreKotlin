package honey.mvpexample

interface HomePresenter {
    fun setDetail(detailData : String)
    fun setView(view : HomeView)
}