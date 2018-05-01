new Vue({
	el: '#develop',
	data: {
		show: 'books',
		buttonStyle: {
			display: 'inline-block',
			float: 'left',
			width: '200px'
		},
		inProgressBooks: [
			{title: 'Kod doskonały 2', author: 'Steve McConnell', pages: 898, current: 125, times: 0, progress: 3, progressClass: 'showProgressElement'},
			{title: 'Java 8. Przewodnik doświadczonego programisty', author: 'Cay S. Horstmann', pages: 414, current: 297, times: 0},
			{title: 'Refaktoryzacja. Ulepszanie struktury istniejącego kodu', author: 'Martin Fowler, Kent Beck, John Brant, William Opdyke, Don Roberts, Erich Gamma', pages: 384, current: 71, times: 0, progress: 54, progressClass: 'showProgressElement'},
			{title: 'Grama to nie drama 1', author: 'Arlena Witt', pages: 271, current: 82, times: 0},
			{title: 'Wzorce projektowe. Elementy oprogramowania obiektowego wielokrotnego użytku', author: 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', pages: 356, current: 50, times: 0, progress: 19, progressClass: 'showProgressElement'},
		],
		booksToRead: [
			{title: 'Rozmowa rekrutacyjna dla porgramistów', author: 'Gayle Lakkmann McDowell', pages: 485, current: 0, times: 0},
			{title: 'Grama to nie drama 2', author: 'Arlena Witt', pages: 249, current: 0, times: 0},
			{title: 'Biblia e-biznesu 2', author: 'Maciej Dutko', pages: 772, current: 0, times: 0},
			{title: 'Introwertyzm to zaleta', author: 'Marti Olsen', pages: 308, current: 0, times: 0},
			{title: 'Bogaty Ojciec Biedny Ojciec', author: 'Robert Kiyosaki', pages: 147, current: 0, times: 0},
			{title: 'Fastlane Milionera', author: 'MJ DeMarco', pages: 528, current: 0, times: 0},
			{title: 'Java. Techniki zaawansowane', author: 'MJ DeMarco', pages: 944, current: 0, times: 0},
			{title: 'Wzorce SOA', author: 'Arnon Rotem-Gal-Oz', pages: 320, current: 0, times: 0},
			{title: 'Zaufanie, czyli waluta przyszłości', author: 'Michał Szafrański', pages: 400, current: 0, times: 0},
			{title: 'Gotowi na start', author: 'Pat Flynn', pages: 342, current: 0, times: 0}
		],
		readBooks: [
			{title: 'TDD. Sztuka tworzenia dobrego kodu', author: 'Kent Beck', pages: 228, current: 228, times: 1, last: '12.03.2018', progress: 93, progressClass: 'showProgressElement'},
			{title: 'Włam się do mózgu', author: 'Radosław Kotarski', pages: 320, current: 320, times: 1, last: '01.02.2018'},
			{title: 'Finansowy ninja', author: 'Michał Szafrański', pages: 320, current: 320, times: 1, last: '01.06.2017'},
			{title: 'Rusz głową! Wzorce projektowe', author: 'Eric Freeman, Elisabeth Freeman, Bert Bates, Kathy Sierra', pages: 647, current: 647, times: 1, last: '01.08.2017'},
			{title: 'Pragmatyczny programista', author: 'Andrew Hunt, David Thomas', pages: 316, current: 316, times: 1, last: '01.05.2015'},
			{title: 'How to win friends and influence to people', author: 'Dale Carnegie', pages: 299, current: 299, times: 1, last: '01.04.2015'},
			{title: 'Pracuj na tak', author: 'Paweł Kubisiak', pages: 171, current: 171, times: 1, last: '01.09.2015'},
			{title: '125 pytań na temat e-Biznesu', author: 'Piotr Majewski', pages: 224 , current: 224, times: 1, last: '01.02.2015'},
			{title: 'Metoda lean startup', author: 'Eric Ries', pages: 248, current: 248, times: 1, last: '01.04.2015'},
			{title: 'Jak zarabiać na palikacjach i grach mobilnych', author: 'Piotr Stalewski', pages: 135, current: 135, times: 1},
			{title: '7 kroków do własnej firmy', author: 'Tadeusz Bisewski', pages: 208, current: 208, times: 1},
			{title: 'Sprytny biznes', author: 'Marcin Pietraszek', pages: 182, current: 182, times: 1},
			{title: 'Skazany na trening', author: 'Paul Wade', pages: 333, current: 333, times: 1, last: '01.01.2015'},
			{title: 'Kropki', author: 'Włodek Markowicz', pages: 302, current: 302, times: 1, last: '01.07.2016'},
			{title: 'Steve Jobs', author: 'Walter Isaacson', pages: 714, current: 714, times: 1, last: '01.04.2016'},
			{title: 'Obudź w sobie olbrzyma', author: 'Anthony Robbins', pages: 677, current: 677, times: 1},
			{title: 'Zrobię to dzisiaj', author: 'Bartłomiej Popiel', pages: 105, current: 105, times: 1},
			{title: 'Przestań jęczeć i weź się wreszcie do roboty!', author: 'Bartłomiej Popiel', pages: 175, current: 175, times: 1},
			{title: 'Bogaty albo biedny. Po prostu różni mentalnie', author: 'Eker T.Harv', pages: 159, current: 159, times: 1},
			{title: 'Motywacja bez granic', author: 'Nikodem Marszałek', pages: 159, current: 159, times: 1},
			{title: 'Rework', author: 'Jason Fried', pages: 306, current: 306, times: 1},
			{title: 'Siła nawyku', author: 'Duhigg Charles', pages: 424, current: 424, times: 1, last: '01.08.2017'},
			{title: 'Liczy się tylko wynik!', author: 'Larry Winget', pages: 209, current: 209, times: 1},
			{title: 'Jesteś Bogiem. Historia paktofoniki', author: 'Maciej Pisuk', pages: 307, current: 307, times: 1},
			{title: 'Pij graj używaj', author: 'Andrew Gottlieb', pages: 207, current: 207, times: 1},
			{title: 'Zwycięzca jest sam', author: 'Paulo Coelho', pages: 347, current: 347, times: 1},
			{title: 'Alchemik', author: 'Paulo Coelho', pages: 187, current: 187, times: 3},
			{title: 'Weronika postanawia umrzeć', author: 'Paulo Coelho', pages: 216, current: 216, times: 1}
		],
		inProgressCourses: [
			{title: 'Vue JS 2 - The Complete Guide', author: 'Maximilian Schwarzmüller', pages: 386, current: 74, times: 0, progress: 19, progressClass: 'showProgressElement'},
			{title: 'Spring Framework Masterclass: Beginner to Professional', author: 'Tim Buchalka', pages: 165, current: 61, times: 0},
			{title: 'Cucumber with Selenium Java', author: 'Karthik KK', pages: 20, current: 4, times: 0},
		],
		coursesToWatch: [
			{title: 'Master Jenkins CI For DevOps and Developers', author: 'Level Up', pages: 59, current: 1, times: 0},
			{title: 'Docker Mastery: The Complete Toolset From a Docker Captain', author: 'Bret Fisher', pages: 89, current: 5, times: 0},
			{title: 'UML Fundamentals', author: 'Infinite Skills', pages: 62, current: 0, times: 0},
			{title: 'Nuxt.js - Vue.js on Steroids', author: 'Maximilian Schwarzmüller', pages: 106, current: 5, times: 0}
		],
		doneCourses: [
			{title: 'Practical Data Structures & Algorithms in Java + HW', author: 'Imtiaz Ahmad', pages: 40, current: 40, times: 1, last: '20.04.2017'},
			{title: 'Complete Java Masterclass', author: 'Tim Buchalka', pages: 330, current: 330, times: 1, last: '01.02.2017'},
			{title: 'Spring & Hibernate for Beginners', author: 'Chad Darby', pages: 218, current: 218, times: 1, last: '01.01.2017'},
			{title: '11 Essential Coding Interview Questions + Coding Exercises!', author: 'YK Sugishita', pages: 56, current: 56, times: 1, last: '01.01.2018'}
		],
		myfitnesspal: [
		{date: '', url: 'http://www.myfitnesspal.com/reports/printable_diary/Dolti?from=2017-03-01&to=2017-03-01'}
		]
	},
	created: function(){
		this.hideProgressesAfter(2000);
	},
	methods:{
		hideProgressesAfter: function(time){
			var vm = this;
			var tab;
			switch(this.show){
				case 'books':
					tab = vm.inProgressBooks;
				break;
				case 'courses':
					tab = vm.inProgressCourses;
				break;
			}
			setTimeout(function(){
				for(var i = 0; i < tab.length; i++){
					if(tab[i].progress > 0){
						tab[i].progressClass = 'hideProgressElement';
					}}},time);
		},
		showProgress: function(tab){
				for(var i = 0; i < tab.length; i++){
					if(tab[i].progress > 0){
						tab[i].progressClass = 'showProgressElement';
					}
				}
				this.hideProgressesAfter(2000);
		},
		selectTab: function(name){
			var tab;
			this.show = name;
			switch(name){
				case 'books':
					document.getElementById('booksTabButton').classList.add('developMenuButtonPressed');
					document.getElementById('coursesTabButton').classList.remove('developMenuButtonPressed');
					tab = this.inProgressBooks;
				break;
				case 'courses': 
					document.getElementById('coursesTabButton').classList.add('developMenuButtonPressed'); 
					document.getElementById('booksTabButton').classList.remove('developMenuButtonPressed');
					tab = this.inProgressCourses;
					
				break;
			}
			this.showProgress(tab);
		},
		showProgressNumber: function(bookInProgress){
			bookInProgress.progressClass = 'showProgressElement';
		},
		hideProgressNumber: function(bookInProgress){
			bookInProgress.progressClass = 'hideProgressElement';
		}
	}
	});