$('#deleteConfirmationModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	var billCode = button.data('billcode');
	var billDescription = button.data('billdescription');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += '/';
	}
	
	form.attr('action', action + billCode);
	
	modal.find('.modal-body span').html('Do you really want to delete the bill <strong>' + billDescription + ' </strong>? This process cannot be undone.')
})

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal:'.', thousands:' ',allowZero: true});
	
	$('.js-update-status').on('click', function(event) {
		event.preventDefault();
		
		const payButton = $(event.currentTarget);
		const payUrl = payButton.attr('href');
		
		let response = $.ajax({
			url: payUrl,
			type: 'PUT'
		});
		
		response.done(function(e) {
			const billCode = payButton.data('code');
			$('[data-role=' + billCode + ']').html('<span class="label label-success">' + e + '</span>');
			payButton.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('An error occurred during updating the bill status process.')
		});
		
	})
});
