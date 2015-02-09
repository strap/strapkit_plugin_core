
var exec = require('../exec'); //
var klass = require('../utils/klass');

var index = 1;

var getPageIndex = function() {
	return index++;
};

var Page = klass({
	id: null,
	views: [],
	pageOpen: false,
	backgroundColor: null,
	initialize: function(views) {
		if (typeof views === 'Array') {
			this.views = views;
		} else if (views !== null && typeof views === 'object') {
			this.views = [];
			this.views.push(views);
		} else {
			this.views = [];
		}
	},
	setId: function(idx) {
		if (this.id == null) {
			this.id = idx;
		}
	},
	getId: function() {
		return this.id;
	},
	addView: function(view) {
		this.views.push(view);
	},
	getViews: function() {
		return this.views;
	},
	setBackgroundColor: function(color) {
		this.backgroundColor = color;
	},
	show: function(success, error) {
		this.pageOpen = true;
		this.setId(getPageIndex());
		var viewString = [];
		for (var i = 0; i < this.getViews().length; i++) {
			var view = this.getViews()[i];
			viewString.push(view.getJSON());
		}
		var args = {
			id: this.getId(),
			views: viewString,
			pageOpen: this.pageOpen,
			backgroundColor: this.backgroundColor
		};	
		exec(success, error, "Page", "show", args);
	},
	hide: function(success, error) {
		this.pageOpen = false;
		var args = {
			id: this.getId()
		}
		exec(success, error, "Page", "hide", args);
	}
});

module.exports = Page;
