'use strict';

(function($){
	$.fn.extend({
		// S : fn_datepicker
		fn_datepicker : function(options){
			var defaults = {};
			var opts = $.extend(defaults, options);
			return this.each(function(){
				var $this = $(this),
					range = $this.attr('data-range'),
					from = $this.attr('data-from'),
					to = $this.attr('data-to'),
					minDate, maxDate, $elm, optDate,
					enableDates = opts.enableDates,
					onSelect = opts.onSelect;

				var dateOptions = {
					dafaultDate : new Date(),
					showMonthAfterYear : true,
					showOtherMonths : true,
					changeYear : true,
					changeMonth : true,
					yearRange : '1940 : +10',
					ignoreReadonly : true,
					showOn : 'button',
					buttonText : '날짜 선택',
					showButtonPanel : true,
					onClose : function(dateText, inst){
						$(inst.trigger).focus();
						dpAccessibility.closeCalendar(inst);

						if($elm !== undefined) $elm.datepicker('option', optDate, dateText);
					},
					beforeShow : function(input, inst){
						dpAccessibility.openCalendar(input, inst);
					}
				}
				if($this.attr('disabled') || $this.attr('readonly')) dateOptions.disabled = true

				if(range !== undefined && $.trim(range) != ''){
					var arrRange = range.split(',')
					dateOptions.minDate =$.trim(arrRange[0]);
					dateOptions.maxDate =$.trim(arrRange[1]);
				}

				if(from !== undefined && $.trim(from) != ''){
					$elm = $this.parent().addClass('items').closest('.date-picker').find($('[data-to]'));
					optDate = 'minDate';
				}

				if(to !== undefined && $.trim(to) != ''){
					$elm = $this.parent().addClass('items').closest('.date-picker').find('[data-from]');
					optDate = 'maxDate';
				}

				if(enableDates !== undefined){
					dateOptions.beforeShowDay = function(d){
						var dmy = d.getMonth() + 1;
						if(d.getMonth() < 9) dmy = '0' + dmy;
						dmy += '-';

						if(d.getDate()<10) dmy += '0';
						dmy = d.getFullYear() + '-' + dmy + d.getDate();

						if($.inArray(dmy, enableDates) != -1){
							return [true, 'ui-datepicker-current-day'];
						}else{
							return [false, ''];
						}
					}
				}

				if(onSelect !== undefined) dateOptions.onSelect = onSelect;

				var now = new Date(),
					day = ('0' + now.getDate()).slice(-2),
					month = ('0' + (now.getMonth() + 1)).slice(-2),
					today = now.getFullYear()+'-'+(month)+'-'+(day);

				//$this.val(today); // today default setting

				$.datepicker.regional['ko']={
					closeText:'닫기',
					prevText:'이전달',
					nextText:'다음달',
					currentText:'오늘',
					monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
					monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
					dayNames:['일','월','화','수','목','금','토'],
					dayNamesShort:['일','월','화','수','목','금','토'],
					dayNamesMin:['일','월','화','수','목','금','토'],
					weekHeader:'Wk',
					dateFormat:'yy-mm-dd',
					firstDay:0,
				}

				$.datepicker.setDefaults($.datepicker.regional['ko']);
				$this.attr({'maxlength':'10','numberOnly':''}).datepicker('destroy').datepicker(dateOptions);
				if($this.attr('readonly')) $this.removeAttr('disabled').next().removeAttr('disabled').attr('readonly', true);

				$.datepicker._gotoToday = function(id){
					var target = $(id);
					var inst = this._getInst(target[0]);
					if(this._get(inst, 'gotoCurrent') && inst.currentDay){
						inst.selectedDay = inst.currentDay;
						inst.drawMonth = inst.selectedMonth = inst.currentMonth;
						inst.drawYear = inst.selectedYear = inst.currentYear;
					}else{
						var date = new Date();
						inst.selectedDay = date.getDate();
						inst.drawMonth = inst.selectedMonth = date.getMonth();
						inst.drawYear = inst.selectedYear = date.getFullYear();
						this._setDateDatepicker(target, date);
						this._selectDate(id, this._getDateDatepicker(target));
					}
					this._notifyChange(inst);
					this._adjustDate(target);

					this._setDateDatepicker(target, new Date());
					this._selectDate(id, this._getDateDatepicker(target));
				}

				$.datepicker._selectMonthYear = function(id, select, period){
					var target = $(id),
						inst = this._getInst(target[0]);

					inst['selected' + (period === 'M' ? 'Month' : 'Year')] =
					inst['draw' + (period === 'M' ? 'Month' : 'Year')] =
					parseInt(select.options[select.selectedIndex].value, 10);

					this._notifyChange(inst);
					this._adjustDate(target);

					period == 'Y' ? $('.ui-datepicker-year').focus() : $('.ui-datepicker-month').focus();
					$('.ui-datepicker-year').attr({'title':'연도 선택', 'data-select':inst.selectedYear});
					$('.ui-datepicker-month').attr({'title':'월 선택', 'data-select':inst.selectedMonth+1+'월'});
					$('.ui-datepicker-header a').attr({'role':'button','href':'javascript:void(0)'});
					dpAccessibility.prepHighlightState();
				}

				$this.on('keypress keyup', function(e){
					if(e.which < 48 || e.which > 57) e.preventDefault();
					this.value = this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
					var num_arr = [97, 98, 99, 100, 101, 102, 103, 104, 105, 96,48, 49, 50, 51, 52, 53, 54, 55, 56, 57];
					var key_code = (e.which) ? e.which : e.keyCode;
					if(num_arr.indexOf(Number(key_code)) != -1) if(this.value.length == 4 || this.value.length == 7) this.value += "-";
				});
			});
		},// E : fn_datepicker
	});
})(jQuery);

// S : datepicker 웹접근성
var dpAccessibility = {
	openCalendar : function(input, inst){
		$(inst.trigger).focus();
		$(inst.input).attr('disabled','disabled');
		setTimeout(function(){
			$(inst.input).removeAttr('disabled');
			var tabIdx = $(inst.dpDiv).find('a');
			tabIdx.each(function(){$(this).attr('tabindex','-1')});

			$('.ui-state-highlight').removeClass('ui-state-highlight');
			$('.ui-state-active').addClass('ui-state-highlight');

			if($('.ui-datepicker-today').lenght > 0) $('.ui-datepicker-today').hasClass('ui-state-disabled') ? $('.ui-datepicker-days-cell-over a').addClass('ui-state-highlight') : $('.ui-datepicker-today a').addClass('ui-state-highlight ui-state-active');
			else $('.ui-datepicker-days-cell-over a').addClass('ui-state-highlight');

			var today = $('.ui-datepicker-current-day a')[0];

			if(!today) today = $('.ui-datepicker-days-cell-over a')[0] || $('.ui-state-active')[0] || $('.ui-state-default')[0];
			else if($(inst.dpDiv).hasClass('onlyMonth')) today = $('.ui-datepicker-year')[0];

			today.focus();
			dpAccessibility.datePickHandler(inst);

			$(document).on('click', '.ui-datepicker-prev, .ui-datepicker-next', function(e){
				dpAccessibility.updateHeaderElements()
				$(e.target).hasClass('ui-datepicker-prev') || $(e.target).parent().hasClass('ui-datepicker-prev') ? dpAccessibility.handlePrevClicks() : dpAccessibility.handleNextClicks();
			});
		},0);
	},

	closeCalendar : function(inst){
		$(inst.dpDiv).off('keydown');
		var tabIdx = $(inst.dpDiv).find('a');
		tabIdx.each(function(){$(this).removeAttr('tabindex')});
	},

	datePickHandler : function(inst){
		var activeDate;
		var container = $(inst.dpDiv);
		var input = $('.datepicker, datepicker-month');

		if(!container || !input) return;

		container.attr({'role':'application', 'aria-label':'Calendar view date-picker'});

		var prev = $('.ui-datepicker-prev', container)[0];
		var next = $('.ui-datepicker-next', container)[0];

		next.href = 'javascript:void(0)';
		prev.href = 'javascript:void(0)';

		next.setAttribute('role', 'button');
		prev.setAttribute('role', 'button');

		dpAccessibility.appendOffscreenMonthText(next);
		dpAccessibility.appendOffscreenMonthText(prev);

		dpAccessibility.monthDayYearText();

		$(container).on('keydown', function calendarKeyboardListener(keyVent){
			var which = keyVent.which;
			var target = keyVent.target;
			var dateCurrent = dpAccessibility.getCurrentDate(container);
		
			if(!dateCurrent){
				dateCurrent = $('a.ui-state-default')[0];
				dpAccessibility.setHighlightState(dateCurrent, container);
			}

			if(27 === which){
				keyVent.stopPropagation();
				return $('.ui-datepicker-close').trigger('click');

			}else if(which === 9 && keyVent.shiftKey){ // SHIFT + TAB
				keyVent.preventDefault();
				if($(target).hasClass('ui-priority-secondary')){
					if($('.ui-datepicker-current-day').length>0){
						$('.ui-datepicker-current-day a')[0].focus();
						$('.ui-datepicker-current-day a').attr('tabindex','-1');
					}else{
						$('.ui-state-highlight')[0].focus();
						$('.ui-state-highlight').attr('tabindex','-1');
					}

				}else if($(target).hasClass('ui-datepicker-close')){
					if($('.ui-priority-secondary').length>0){
						$('.ui-priority-secondary')[0].focus();
					}else{
						if($('.ui-datepicker-current-day').length>0){
							$('.ui-datepicker-current-day a')[0].focus();
							$('.ui-datepicker-current-day a').attr('tabindex','-1');
						}else{
							$('.ui-state-highlight')[0].focus();
							$('.ui-state-highlight').attr('tabindex','-1');
						}
					}

				}else if($(target).hasClass('ui-state-default')){
					$('.ui-datepicker-next').attr('tabindex','-1').focus();

				}else if($(target).hasClass('ui-datepicker-year')){
					$('#ui-datepicker-div').hasClass('onlyMonth') ? activeDate = $('.ui-datepicker-close')[0] : activeDate = $('.ui-state-highlight') || $('.ui-state-active')[0];
					if(activeDate) $('.ui-datepicker-prev').attr('tabindex','-1').focus();

				}else if($(target).hasClass('ui-datepicker-month')){
					$('.ui-datepicker-year')[0].focus();

				}else if($(target).hasClass('ui-datepicker-prev')){
					$('.ui-datepicker-close')[0].focus();

				}else if($(target).hasClass('ui-datepicker-next')){
					$('.ui-datepicker-month')[0].focus();
				}

			}else if(which === 9){ // TAB
				keyVent.preventDefault();
				if($(target).hasClass('ui-priority-secondary')){
					$('.ui-datepicker-close')[0].focus();

				}else if($(target).hasClass('ui-datepicker-close')){
					$('#ui-datepicker-div').hasClass('onlyMonth') ? activeDate = $('.ui-datepicker-year')[0] : activeDate = $('.ui-state-highlight') || $('.ui-state-active')[0];
					if(activeDate) $('.ui-datepicker-prev').attr('tabindex','-1').focus();

				}else if($(target).hasClass('ui-state-default')){
					$('.ui-priority-secondary').length>0 ? $('.ui-priority-secondary')[0].focus() : $('.ui-datepicker-close')[0].focus();

				}else if($(target).hasClass('ui-datepicker-prev')){
					$('.ui-datepicker-year')[0].focus();

				}else if($(target).hasClass('ui-datepicker-year')){
					$('.ui-datepicker-month')[0].focus();

				}else if($(target).hasClass('ui-datepicker-month')){
					$('.ui-datepicker-next').attr('tabindex','-1').focus();

				}else if($(target).hasClass('ui-datepicker-next')){
					if($('.ui-datepicker-current-day').length>0){
						$('.ui-datepicker-current-day a')[0].focus();
						$('.ui-datepicker-current-day a').attr('tabindex','-1');
					}else{
						$('.ui-state-highlight')[0].focus();
						$('.ui-state-highlight').attr('tabindex','-1');
					}
				}

			}else if(which === 37){ // LEFT arrow key
				if(!$(target).hasClass('ui-datepicker-close') && $(target).hasClass('ui-state-default') && !$(target).hasClass('ui-priority-secondary')){
					keyVent.preventDefault();
					dpAccessibility.previousDay(target);
				}
			}else if(which === 39){ // RIGHT arrow key
				if(!$(target).hasClass('ui-datepicker-close') && $(target).hasClass('ui-state-default') && !$(target).hasClass('ui-priority-secondary')){
					keyVent.preventDefault();
					dpAccessibility.nextDay(target);
				}
			}else if(which === 38){ // UP arrow key
				if(!$(target).hasClass('ui-datepicker-close') && $(target).hasClass('ui-state-default') && !$(target).hasClass('ui-priority-secondary')){
					keyVent.preventDefault();
					dpAccessibility.upHandler(target, container, prev);
				}
			}else if(which === 40){ // DOWN arrow key
				if(!$(target).hasClass('ui-datepicker-close') && $(target).hasClass('ui-state-default') && !$(target).hasClass('ui-priority-secondary')){
					keyVent.preventDefault();
					dpAccessibility.downHandler(target, container, next);
				}
			}else if(which === 13){ // ENTER
				if($(target).hasClass('ui-datepicker-prev')){
					dpAccessibility.handlePrevClicks();
				}else if($(target).hasClass('ui-datepicker-next')){
					dpAccessibility.handleNextClicks();
				}
			}else if(32 === which){
				if($(target).hasClass('ui-datepicker-prev') || $(target).hasClass('ui-datepicker-next')){
					target.click();
				}
			}else if(33 === which){ // PAGE UP
				keyVent.preventDefault();
				dpAccessibility.moveOneMonth(target, 'prev');
			}else if(34 === which){ // PAGE DOWN
				keyVent.preventDefault();
				dpAccessibility.moveOneMonth(target, 'next');
			}else if(36 === which){ // HOME
				keyVent.preventDefault();
				var firstOfMonth = $(target).closest('tbody').find('a.ui-state-default')[0];
				if(firstOfMonth){
					$(firstOfMonth).attr('tabindex','-1').focus();
					dpAccessibility.setHighlightState(firstOfMonth, $('#ui-datepicker-div')[0]);
				}
			}else if(35 === which){ // END
				keyVent.preventDefault();
				var $daysOfMonth = $(target).closest('tbody').find('a.ui-state-default');
				var lastDay = $daysOfMonth[$daysOfMonth.length - 1];
				if(lastDay){
					$(lastDay).attr('tabindex','-1').focus();
					dpAccessibility.setHighlightState(lastDay, $('#ui-datepicker-div')[0]);
				}
			}
		});
	},
	
	moveOneMonth : function(currentDate, dir){
		var button = (dir === 'next') ? $('.ui-datepicker-next')[0] : $('.ui-datepicker-prev')[0];

		if(!button) return;

		var ENABLED_SELECTOR = '#ui-datepicker-div tbody td:not(.ui-state-disabled)';
		var $currentCells = $(ENABLED_SELECTOR);
		var currentIdx = $.inArray(currentDate.parentNode, $currentCells);

		button.click();
		setTimeout(function(){
			dpAccessibility.updateHeaderElements();

			var $newCells = $(ENABLED_SELECTOR);
			var newTd = $newCells[currentIdx];
			var newAnchor = newTd && $(newTd).find('a')[0];

			while (!newAnchor){
				currentIdx--;
				newTd = $newCells[currentIdx];
				newAnchor = newTd && $(newTd).find('a')[0];
			}

			dpAccessibility.setHighlightState(newAnchor, $('#ui-datepicker-div')[0]);
			$(newAnchor).attr('tabindex','-1').focus();
		}, 0);
	},

	handleNextClicks : function(){
		dpAccessibility.prepHighlightState();
		$('.ui-datepicker-next').attr('tabindex','-1').focus();
	},

	handlePrevClicks : function(){
		dpAccessibility.prepHighlightState();
		$('.ui-datepicker-prev').attr('tabindex','-1').focus();
	},

	previousDay : function(dateLink){
		var container = document.getElementById('ui-datepicker-div');
		if(!dateLink) return;

		var td = $(dateLink).closest('td');
		if(!td) return;

		var prevTd = $(td).prev();
		var prevDateLink = $('a.ui-state-default', prevTd)[0];

		if(prevTd && prevDateLink){
			dpAccessibility.setHighlightState(prevDateLink, container);
			$(prevDateLink).attr('tabindex','-1').focus();
		}else{
			dpAccessibility.handlePrevious(dateLink);
		}
	},

	handlePrevious : function(target){
		var container = document.getElementById('ui-datepicker-div');
		if(!target) return;

		var currentRow = $(target).closest('tr');
		if(!currentRow) return;
		
		var previousRow = $(currentRow).prev();

		if(!previousRow || previousRow.length === 0){
			dpAccessibility.previousMonth();
		}else{
			var prevRowDates = $('td a.ui-state-default', previousRow);
			var prevRowDate = prevRowDates[prevRowDates.length - 1];

			if(prevRowDate){
				setTimeout(function(){
					dpAccessibility.setHighlightState(prevRowDate, container);
					$(prevRowDate).attr('tabindex','-1').focus();
				}, 0);
			}
		}
	},

	previousMonth : function(){
		var prevLink = $('.ui-datepicker-prev')[0];
		var container = document.getElementById('ui-datepicker-div');
		prevLink.click();
		setTimeout(function(){
			var trs = $('tr', container);
			var lastRowTdLinks = $('td a.ui-state-default', trs[trs.length - 1]);
			var lastDate = lastRowTdLinks[lastRowTdLinks.length - 1];

			dpAccessibility.updateHeaderElements();

			dpAccessibility.setHighlightState(lastDate, container);
			$(lastDate).attr('tabindex','-1').focus();
		}, 0);
	},

	nextDay : function(dateLink){
		var container = document.getElementById('ui-datepicker-div');
		if(!dateLink) return;
		
		var td = $(dateLink).closest('td');
		if(!td) return;

		var nextTd = $(td).next();
		var nextDateLink = $('a.ui-state-default', nextTd)[0];

		if(nextTd && nextDateLink){
			dpAccessibility.setHighlightState(nextDateLink, container);
			$(nextDateLink).attr('tabindex','-1').focus();
		}else{
			dpAccessibility.handleNext(dateLink);
		}
	},

	handleNext : function(target){
		var container = document.getElementById('ui-datepicker-div');
		if(!target) return;
		
		var currentRow = $(target).closest('tr');
		var nextRow = $(currentRow).next();

		if(!nextRow || nextRow.length === 0){
			dpAccessibility.nextMonth();
		}else{
			var nextRowFirstDate = $('a.ui-state-default', nextRow)[0];
			if(nextRowFirstDate){
				dpAccessibility.setHighlightState(nextRowFirstDate, container);
				$(nextRowFirstDate).attr('tabindex','-1').focus();
			}
		}
	},

	nextMonth : function(){
		var nextMon = $('.ui-datepicker-next')[0];
		var container = document.getElementById('ui-datepicker-div');
		nextMon.click();
		setTimeout(function(){
			dpAccessibility.updateHeaderElements();

			var firstDate = $('a.ui-state-default', container)[0];
			dpAccessibility.setHighlightState(firstDate, container);
			$(firstDate).attr('tabindex','-1').focus();
		}, 0);
	},

	upHandler : function(target, cont, prevLink){
		prevLink = $('.ui-datepicker-prev')[0];
		var rowContext = $(target).closest('tr');
		if(!rowContext) return;

		var rowTds = $('td', rowContext),
			rowLinks = $('a.ui-state-default', rowContext),
			targetIndex = $.inArray(target, rowLinks),
			prevRow = $(rowContext).prev(),
			prevRowTds = $('td', prevRow),
			parallel = prevRowTds[targetIndex],
			linkCheck = $('a.ui-state-default', parallel)[0];

		if(prevRow && parallel && linkCheck){
			dpAccessibility.setHighlightState(linkCheck, cont);
			$(linkCheck).attr('tabindex','-1').focus();
		}else{
			prevLink.click();
			setTimeout(function(){
				dpAccessibility.updateHeaderElements();
				var newRows = $('tr', cont),
					lastRow = newRows[newRows.length - 1],
					lastRowTds = $('td', lastRow),
					tdParallelIndex = $.inArray(target.parentNode, rowTds),
					newParallel = lastRowTds[tdParallelIndex],
					newCheck = $('a.ui-state-default', newParallel)[0];

				if(lastRow && newParallel && newCheck){
					dpAccessibility.setHighlightState(newCheck, cont);
					$(newCheck).attr('tabindex','-1').focus();
				}else{
					var secondLastRow = newRows[newRows.length - 2],
						secondTds = $('td', secondLastRow),
						targetTd = secondTds[tdParallelIndex],
						linkCheck = $('a.ui-state-default', targetTd)[0];

					if(linkCheck){
						dpAccessibility.setHighlightState(linkCheck, cont);
						$(linkCheck).attr('tabindex','-1').focus();
					}
				}
			}, 0);
		}
	},

	downHandler : function(target, cont, nextLink){
		nextLink = $('.ui-datepicker-next')[0];
		var targetRow = $(target).closest('tr');
		if(!targetRow) return;

		var targetCells = $('td', targetRow),
			cellIndex = $.inArray(target.parentNode, targetCells), 
			nextRow = $(targetRow).next(),
			nextRowCells = $('td', nextRow),
			nextWeekTd = nextRowCells[cellIndex],
			nextWeekCheck = $('a.ui-state-default', nextWeekTd)[0];

		if(nextRow && nextWeekTd && nextWeekCheck){
			dpAccessibility.setHighlightState(nextWeekCheck, cont);
			$(nextWeekCheck).attr('tabindex','-1').focus();
		}else{
			nextLink.click();

			setTimeout(function(){
				dpAccessibility.updateHeaderElements();

				var nextMonthTrs = $('tbody tr', cont),
					firstTds = $('td', nextMonthTrs[0]),
					firstParallel = firstTds[cellIndex],
					firstCheck = $('a.ui-state-default', firstParallel)[0];

				if(firstParallel && firstCheck){
					dpAccessibility.setHighlightState(firstCheck, cont);
					$(firstCheck).attr('tabindex','-1').focus();
				}else{
					var secondRow = nextMonthTrs[1],
						secondTds = $('td', secondRow),
						secondRowTd = secondTds[cellIndex],
						secondCheck = $('a.ui-state-default', secondRowTd)[0];

					if(secondRow && secondCheck){
						dpAccessibility.setHighlightState(secondCheck, cont);
						$(secondCheck).attr('tabindex','-1').focus();
					}
				}
			}, 0);
		}
	},

	monthDayYearText : function(){
		var cleanUps = $('.amaze-date');

		$(cleanUps).each(function (clean){
			clean.parentNode.removeChild(clean);
		});

		var datePickDiv = document.getElementById('ui-datepicker-div');
		if(!datePickDiv) return;

		var dates = $('a.ui-state-default', datePickDiv);
		$(dates).attr('role', 'button').on('keydown', function (e){
			if(e.which === 32){
				e.preventDefault();
				e.target.click();
				setTimeout(function(){
					dpAccessibility.closeCalendar();
				}, 100);
			}
		});
		$(dates).each(function (index, date){
			var currentRow = $(date).closest('tr'),
				currentTds = $('td', currentRow),
				currentIndex = $.inArray(date.parentNode, currentTds),
				headThs = $('thead tr th', datePickDiv),
				dayIndex = headThs[currentIndex],
				daySpan = $('span', dayIndex)[0],
				monthName = $('.ui-datepicker-month option:selected').text(),
				year = $('.ui-datepicker-year option:selected').text(),
				number = date.innerHTML;

			if(!daySpan || !monthName || !number || !year) return;

			var dateText = year + '년 ' + monthName + ' ' + date.innerHTML + '일 ' + daySpan.title + '요일';
			date.setAttribute('aria-label', dateText);
		});
	},

	updateHeaderElements : function(){
		var context = document.getElementById('ui-datepicker-div');
		if(!context) return;

		var prev = $('.ui-datepicker-prev')[0];
		var next = $('.ui-datepicker-next')[0];

		next.href = 'javascript:void(0)';
		prev.href = 'javascript:void(0)';

		next.setAttribute('role','button');
		prev.setAttribute('role','button');

		dpAccessibility.appendOffscreenMonthText(next);
		dpAccessibility.appendOffscreenMonthText(prev);

		dpAccessibility.monthDayYearText();
	},

	prepHighlightState : function(){
		var highlight;
		var cage = document.getElementById('ui-datepicker-div');

		highlight = $('a.ui-state-active', cage)[0] || $('.ui-datepicker-today a', cage)[0] || $('a.ui-state-default', cage)[0] || $('.ui-state-highlight', cage)[0];

		if(highlight && cage) dpAccessibility.setHighlightState(highlight, cage);
	},

	setHighlightState : function(newHighlight, container){
		var prevHighlight = dpAccessibility.getCurrentDate(container);
		if($('.ui-state-highlight').length>0){
			$(prevHighlight).removeClass('ui-state-highlight');
			$(newHighlight).addClass('ui-state-highlight');
		}else{
			$(newHighlight).addClass('ui-state-highlight');
		}
	},

	getCurrentDate : function(container){
		var currentDate = $('.ui-state-highlight', container)[0];
		return currentDate;
	},

	appendOffscreenMonthText : function(button){
		var buttonText;
		var isNext = $(button).hasClass('ui-datepicker-next');
		var months = ['1월', '2월','3월', '4월','5월', '6월', '7월','8월', '9월','10월','11월', '12월'];

		$('.ui-datepicker-year').attr({'title':'연도 선택', 'data-select':$('.ui-datepicker-year option:selected').text()});
		$('.ui-datepicker-month').attr({'title':'월 선택', 'data-select':$('.ui-datepicker-month option:selected').text()});

		if($(button).closest('.ui-datepicker').find('.ui-datepicker-title select').hasClass('ui-datepicker-year')){
			var currentMonth = $('.ui-datepicker-title .ui-datepicker-month').attr('data-select').toLowerCase();
			var monthIndex = $.inArray(currentMonth.toLowerCase(), months);
			var currentYear = $('.ui-datepicker-title .ui-datepicker-year').attr('data-select').toLowerCase();
			var adjacentIndex = (isNext) ? monthIndex + 1 : monthIndex - 1;
		}else{
			var currentMonth = $('.ui-datepicker-title .ui-datepicker-month').text().toLowerCase();
			var monthIndex = $.inArray(currentMonth.toLowerCase(), months);
			var currentYear = $('.ui-datepicker-title .ui-datepicker-year').text().toLowerCase();
			var adjacentIndex = (isNext) ? monthIndex + 1 : monthIndex - 1;
		}

		if(isNext && currentMonth === '12월'){
			currentYear = parseInt(currentYear, 10);
			adjacentIndex = 0;
		}else if(!isNext && currentMonth === '1월'){
			currentYear = parseInt(currentYear, 10);
			adjacentIndex = months.length - 1;
		}

		buttonText = (isNext) ? '다음 달, ' + currentYear +'년 ' + dpAccessibility.firstToCap(months[adjacentIndex]) : '이전 달, '+ currentYear +'년 ' + dpAccessibility.firstToCap(months[adjacentIndex]);
		$(button).attr('title', buttonText).find('.ui-icon').html(buttonText);
	},

	firstToCap : function(s){
		return s.charAt(0).toUpperCase() + s.slice(1);
	},
}
// E : datepicker 웹접근성

// document ready
$(function(){
	
});


$(window).on('load', function(){
	
});

// 스크롤시 제어 클래스
$(window).scroll(function() {
	var scroll = $(window).scrollTop();
	if (scroll >= 50) {
		$("body").addClass("scroll");
	} else {
		$("body").removeClass("scroll");
	}
});