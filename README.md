Design assumption violated error in FragmentStateAdapter
===

Reported Issue on Android Public Tracker
- https://issuetracker.google.com/issues/134246546

## The Scenario

1. ViewPager content view(s) starts animation via ViewPropertyAnimator
2. ViewPropertyAnimator invokes View.setHasTransientState(true) internally
3. When the underlying RecyclerView tries to recycle a page view, it fails to recycle the view. Because ViewHolder.isRecyclable() returns false due to ViewGroup/View.hasTransientState() returns true.
4. FragmentStateAdapter.onFailedToRecycleView() invoked and then it calls onViewRecycled().
5. FragmentStateAdapter.placeFragmentInViewHolder() invoked, but the corresponding fragment cannot be retrieved.
6. IllegalStateException("Design assumption violated.") thrown
